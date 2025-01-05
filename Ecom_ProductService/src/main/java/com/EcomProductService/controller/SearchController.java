package com.EcomProductService.controller;

import com.EcomProductService.dto.GenericProductDto;
import com.EcomProductService.dto.SearchRequestDto;
import com.EcomProductService.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }
    @PostMapping
    public Page<GenericProductDto> searchProducts(@RequestBody SearchRequestDto requestDto){
        List<GenericProductDto> genericProductDtos = searchService.searchProducts(requestDto.getTitle(),
                requestDto.getPageNumber(),
                requestDto.getPageSize(),
                requestDto.getSortParams());
          Page<GenericProductDto> genericProductDtoPage = new PageImpl<>(genericProductDtos);
        return genericProductDtoPage;
    }
}
