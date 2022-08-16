package com.project.autocomplete.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductAutoCompleteDto {

  private String id;
  private String name;
  private String image;
  private String url;
}
