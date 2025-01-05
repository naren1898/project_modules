package com.EcomProductService.service;


import com.EcomProductService.Repository.CategoryRepository;
import com.EcomProductService.Repository.PriceRepository;
import com.EcomProductService.Repository.ProductRepository;
import com.EcomProductService.dto.ProductListResponseDTO;
import com.EcomProductService.dto.ProductResponseDTO;
import com.EcomProductService.Mapper.ProductMapper;
import com.EcomProductService.dto.ProductRequestDTO;
import com.EcomProductService.model.Category;
import com.EcomProductService.model.Price;
import com.EcomProductService.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductService_DB")
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;
    //private final OpenSearchProductRepository openSearchProductRepository;
    private final PriceRepository priceRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImp(ProductRepository productRepository,
                             //OpenSearchProductRepository openSearchProductRepository,
                              PriceRepository priceRepository,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        //this.openSearchProductRepository = openSearchProductRepository;
        this.priceRepository = priceRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.productsDTOtoproductListResponseDTO(products);
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        if(id==1){
            Product product_id = productRepository.findByDescription("Best Iphone");
            return ProductMapper.productsDTOtoproductResponseDTO(product_id);
        }
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO product) {
        //System.out.println(product.getDescription());
        Product product_c = new Product();
        product_c.setDescription(product.getDescription());
        product_c.setImage(product.getImage());
        product_c.setTitle(product.getTitle());
        Price price_c = new Price();
        price_c.setAmount(product.getPrice());
        priceRepository.save(price_c);
        product_c.setPrice(price_c);
        Category category_c = new Category();
        category_c.setCategoryName(product.getCategory());
        categoryRepository.save(category_c);
        product_c.setCategory(category_c);
        Product savedproduct = productRepository.save(product_c);
        //openSearchProductRepository.save(savedproduct);
        return ProductMapper.productsDTOtoproductResponseDTO(savedproduct);
    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public Product updateProduct(int id, Product updatedProduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) {
        Product product = productRepository.findByTitle(title);
        return ProductMapper.productsDTOtoproductResponseDTO(product);
    }
}
