package com.example.mk0730.alkolmetre.alcohol;

public class Alcohol {
    private Integer id;
    private String imageUri;
    private String name;
    private String origin;

    public Alcohol(Integer id, String image, String name, String origin) {
        this.id = id;
        this.imageUri = image;
        this.name = name;
        this.origin = origin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
