package com.email.npsplayer00.store.dto;

public class HistoryDto {

    public Long id;
    public ProductDto product;
    public UserDto user;
    public String status;
    public Integer amount;
    public Float price;

    public HistoryDto() {

    }

    public HistoryDto(Long id, ProductDto product, UserDto user, String status, Integer amount, Float price) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.status = status;
        this.amount = amount;
        this.price = price;
    }
}
