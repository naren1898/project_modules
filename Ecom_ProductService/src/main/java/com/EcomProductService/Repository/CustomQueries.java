package com.EcomProductService.Repository;

public interface CustomQueries {

    String FIND_PROD_BY_TITLE="select * from product where title like :Title and id :id";

}
