package com.project.autocomplete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.project.autocomplete.repository.mongo")
@EnableElasticsearchRepositories("com.project.autocomplete.repository.elastic")
public class AutocompleteApplication {

  public static void main(String[] args) {
    SpringApplication.run(AutocompleteApplication.class, args);
  }

}
