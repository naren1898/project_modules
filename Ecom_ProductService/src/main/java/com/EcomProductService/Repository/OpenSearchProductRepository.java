package com.EcomProductService.Repository;

import com.EcomProductService.model.GenericProduct;
import com.EcomProductService.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public interface OpenSearchProductRepository //extends ElasticsearchRepository<Product, UUID>
{
   // @Query("{\"bool\": {\"should\": [{\"more_like_this\": {\"fields\": [\"title\",\"description\"], \"like\": [\"?0\"], \"min_term_freq\": 1, \"max_query_terms\": 12}}]}}")
    //Page<Product> searchSimilar(String query, Pageable pageable);

}

