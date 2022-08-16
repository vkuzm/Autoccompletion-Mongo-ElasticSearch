package com.project.autocomplete.repository.mongo;

import com.project.autocomplete.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductMongoDbRepository extends MongoRepository<Product, String> {

}
