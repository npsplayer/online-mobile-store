package com.email.npsplayer00.store.dto;

public class CartDto {


    public Long id;
    public ProductDto product;
    public UserDto user;
    public Integer amount;
    public Float price;

    public CartDto() {
    }

    public CartDto(ProductDto product, Integer amount, Float price) {
//        this.id = id;
        this.product = product;
//        this.user = user;
        this.amount = amount;
        this.price = price;
    }
}
