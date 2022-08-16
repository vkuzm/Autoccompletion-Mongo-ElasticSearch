package com.project.autocomplete.service;

import com.project.autocomplete.dto.ProductAutoCompleteDto;
import java.util.List;

public interface ProductSearchService {

  List<ProductAutoCompleteDto> findProductsByQuery(String query);
}
