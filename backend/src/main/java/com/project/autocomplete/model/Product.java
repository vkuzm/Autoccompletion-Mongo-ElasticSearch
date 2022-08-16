package com.project.autocomplete.model;

import java.math.BigInteger;
import java.util.List;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@ToString
@Document(indexName = "products")
public class Product {

  @Id
  private String id;
  private String sku;
  private String name;
  private String type;
  private String price;
  private String upc;
  private List<Category> category;
  private String shipping;
  private String description;
  private String manufacturer;
  private String model;
  private String image;
  private String url;
}