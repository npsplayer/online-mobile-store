package com.email.npsplayer00.store.dto;

public class ColorDto {

    public Long id;
    public String name;
    public String colorHex;

    public ColorDto() {
    }

    public ColorDto(Long id, String name, String colorHex) {
        this.id = id;
        this.name = name;
        this.colorHex = colorHex;
    }
}
