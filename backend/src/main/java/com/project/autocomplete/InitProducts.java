package com.project.autocomplete;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.autocomplete.model.Product;
import com.project.autocomplete.repository.elastic.ProductElasticSearchRepository;
import com.project.autocomplete.repository.mongo.ProductMongoDbRepository;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class InitProducts implements ApplicationRunner {

  private static final String DATA_PRODUCTS_JSON = "data/products.json";

  private final ObjectMapper mapper;
  private final ProductMongoDbRepository productMongoDbRepository;
  private final ProductElasticSearchRepository productElasticSearchRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Path path = Paths.get(DATA_PRODUCTS_JSON).toAbsolutePath();
    List<Product> products = Arrays.asList(mapper.readValue(path.toFile(), Product[].class));

    if (productMongoDbRepository.count() == 0) {
      productMongoDbRepository.saveAll(products);
      productElasticSearchRepository.saveAll(products);

      log.info("Added " + products.size() + " products ");
    }
  }
}
