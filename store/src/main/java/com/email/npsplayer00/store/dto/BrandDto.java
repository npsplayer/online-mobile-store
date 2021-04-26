package com.email.npsplayer00.store.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BrandDto {


    public Long id;
    public String name;

    public BrandDto() {
    }

    public BrandDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
