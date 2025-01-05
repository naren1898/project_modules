package com.EcomProductService.Repository;

import com.EcomProductService.model.Product;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitle(String title);
    //Product findById(int id);
    //Product findByTitleOrDescription(String title, String description);
    Product findByTitleAndDescription(String title, String description);
    Product findByPrice_AmountLessThan(double amount);
    Product findByDescription(String description);
    List<Product> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);
    Product deleteByTitle(String title);
    //Product findByPriceGreaterThan(double price);
    //Product findByPriceGreaterThanEqual(double price);
    //Product findByPriceLessThanEqual(double price);
    //Product findByPriceBetweenStartPriceAndEndPrice(double StartPrice, double EndPrice);
    @Query(value=CustomQueries.FIND_PROD_BY_TITLE,nativeQuery=true)
    Product findProductByTitleLikeAndId(String Title, UUID id);
    //@Query(value="select * from products",nativeQuery=true)
    //Product findALLProducts(String Title, UUID id);

}