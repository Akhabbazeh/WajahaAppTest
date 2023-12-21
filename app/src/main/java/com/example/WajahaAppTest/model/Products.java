package com.example.WajahaAppTest.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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

    @SerializedName("primaryImage")
    private PrimaryImage Primary_Image;

    @SerializedName("images")
    private ArrayList<Images> images;

    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public PrimaryImage getPrimary_Image() {
        return Primary_Image;
    }

    public void setPrimary_Image(PrimaryImage primary_Image) {
        Primary_Image = primary_Image;
    }


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

    public boolean getIsFeatured() {

        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public class PrimaryImage {
        @SerializedName("url")
        private String LinkPrimaryImage;

        public String getLinkPrimaryImage() {
            return LinkPrimaryImage;
        }

        public void setLinkPrimaryImage(String linkPrimaryImage) {
            LinkPrimaryImage = linkPrimaryImage;
        }
    }

    public class Images {

        @SerializedName("id")
        private String id;

        @SerializedName("url")
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    }
