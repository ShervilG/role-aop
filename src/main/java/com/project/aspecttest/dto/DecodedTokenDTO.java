package com.project.aspecttest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecodedTokenDTO {
  private String token;
  private List<String> roles = new ArrayList<>();
}
