package com.example.WajahaAppTest.feature.product_feature;

import com.google.gson.annotations.SerializedName;

public class Products {
@SerializedName("name")
private String name;
    @SerializedName("price")
private int price;
    @SerializedName("dateOfPublication")
private String dateOfPublication;
    @SerializedName("description")
private String description;
    @SerializedName("isFeatured")
private boolean isFeatured;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }
}
