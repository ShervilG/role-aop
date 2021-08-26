package com.project.aspecttest.auth;

import com.project.aspecttest.common.Role;
import com.project.aspecttest.exception.UnauthorizedRoleException;
import com.project.aspecttest.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogHeaderAspect {

  @Autowired
  private TokenUtil tokenUtil;

  @Before("@annotation(ValidateRole)")
  public void doSomething(JoinPoint joinPoint) {
    String header = getTokenHeader();
    Role[] roles = getRoles(joinPoint);

    if (roles == null || roles.length == 0 || header == null || header.length() == 0) {
      throw new UnauthorizedRoleException("Missing token or roles !");
    }

    log.info("token is : {}", header);

    if (!verifyRoles(header, roles)) {
      throw new UnauthorizedRoleException("unauthorized role !");
    }
  }

  private String getTokenHeader() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    return request.getHeader("token");
  }

  private Role[] getRoles(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    ValidateRole annotation = method.getAnnotation(ValidateRole.class);
    return annotation.roles();
  }

  private boolean verifyRoles(String token, Role[] validRoles) {
    String[] availableRoles = tokenUtil.getRolesFromToken(token);
    for (Role validRole: validRoles) {
      for (String role: availableRoles) {
        if (role.equals(validRole.getName())) {
          return true;
        }
      }
    }
    return false;
  }
}
