package com.project.autocomplete.controller;

import com.project.autocomplete.dto.ProductAutoCompleteDto;
import com.project.autocomplete.model.Product;
import com.project.autocomplete.repository.mongo.ProductMongoDbRepository;
import com.project.autocomplete.service.ProductSearchService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

  private final ProductMongoDbRepository productMongoDbRepository;
  private final ProductSearchService productSearchService;

  @GetMapping
  public Page<Product> getProducts(@RequestParam(defaultValue = "10") int limit) {
    return productMongoDbRepository.findAll(PageRequest.of(0, limit));
  }

  @GetMapping("/{productId}")
  public Product getProduct(@PathVariable String productId) {
    return productMongoDbRepository.findById(productId).orElseThrow();
  }

  @GetMapping("/search")
  public List<ProductAutoCompleteDto> searchProducts(@RequestParam String q) {
    return productSearchService.findProductsByQuery(q);
  }
}
