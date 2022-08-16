package com.project.autocomplete.repository.elastic;

import com.project.autocomplete.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductElasticSearchRepository extends ElasticsearchRepository<Product, String> {

}
