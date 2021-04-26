package com.email.npsplayer00.store.dto;

public class FavoriteDto {

    public Long id;
    public ProductDto product;
    public UserDto user;

    public FavoriteDto() {
    }

    public FavoriteDto(Long id, ProductDto product, UserDto user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }
}
