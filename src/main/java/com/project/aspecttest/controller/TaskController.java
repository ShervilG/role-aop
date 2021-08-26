package com.project.aspecttest.controller;

import com.project.aspecttest.auth.ValidateRole;
import com.project.aspecttest.common.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/task")
public class TaskController {

  @ValidateRole(roles = {Role.ADMIN, Role.SUPER_ADMIN})
  @GetMapping("/all")
  public String getTasks(String needed) {
    return "All tasks returned";
  }
}
