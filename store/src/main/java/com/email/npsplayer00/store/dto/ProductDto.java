package com.email.npsplayer00.store.dto;

import com.email.npsplayer00.store.entity.Brand;
import com.email.npsplayer00.store.entity.Categorie;

import java.util.Date;
import java.util.Map;

    public class ProductDto {

        public Long id;
        public String name;
        public Float price;
        public String description;
        public Date datecreate;
        public BrandDto brand;
        public CategorieDto categorie;
        public ColorDto color;
        public Map<String, Object> imageUrl;

    public ProductDto() {
    }

        public ProductDto(Long id, String name, Float price, String description, Date datecreate, BrandDto brand, CategorieDto categorie, ColorDto color, Map<String, Object> imageUrl) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
            this.datecreate = datecreate;
            this.brand = brand;
            this.categorie = categorie;
            this.color = color;
            this.imageUrl = imageUrl;
        }
    }
