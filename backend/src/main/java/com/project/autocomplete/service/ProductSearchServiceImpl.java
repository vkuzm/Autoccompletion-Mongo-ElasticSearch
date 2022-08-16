package com.project.autocomplete.service;

import com.project.autocomplete.dto.ProductAutoCompleteDto;
import com.project.autocomplete.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

  private final ElasticsearchOperations elasticsearchOperations;

  @Override
  public List<ProductAutoCompleteDto> findProductsByQuery(String query) {
    QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(query)
        .field("name")
        .field("sku")
        .field("model")
        .field("description")
        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS);

    Query searchQuery = new NativeSearchQueryBuilder()
        .withPageable(Pageable.ofSize(10))
        .withQuery(queryBuilder)
        .build();

    SearchHits<Product> productHits
        = elasticsearchOperations.search(searchQuery, Product.class, IndexCoordinates.of("products"));

    return productHits.stream()
        .map((productSearchHit) -> {
          var product = productSearchHit.getContent();

          return ProductAutoCompleteDto.builder()
              .id(product.getId())
              .name(product.getName())
              .image(product.getImage())
              .url(product.getUrl())
              .build();
        })
        .collect(Collectors.toList());
  }
}
