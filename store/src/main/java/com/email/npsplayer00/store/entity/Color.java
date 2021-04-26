package com.email.npsplayer00.store.entity;

import javax.persistence.*;

@Entity
@Table(name = "color", schema = "public")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color_hex")
    private String colorHex;

    public Color() {
    }

    public Color(String name, String colorHex) {
        this.name = name;
        this.colorHex = colorHex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }
}
