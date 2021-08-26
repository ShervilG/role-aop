package com.project.aspecttest.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.aspecttest.dto.DecodedTokenDTO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenUtil {

  private final ObjectMapper mapper;

  public TokenUtil() {
    mapper = new ObjectMapper();
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
  }

  public String[] getRolesFromToken(String token) {
    String decodedToken = new String(Base64.decode(token));
    try {
      DecodedTokenDTO decodedTokenDTO = mapper.readValue(decodedToken, DecodedTokenDTO.class);
      return decodedTokenDTO.getRoles().toArray(new String[0]);
    } catch (Exception ignore) {
      log.error("Exception : {}", ignore.getMessage());
    }
    return new String[0];
  }
}
