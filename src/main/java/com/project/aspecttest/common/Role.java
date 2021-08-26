package com.project.aspecttest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
  SUPER_ADMIN("SuperAdmin"),
  ADMIN("Admin"),
  LABELER("Labeler");

  private String name;
}
