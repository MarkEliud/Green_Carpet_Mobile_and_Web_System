package com.mk.Green.ServiceManager.Adapters;

public class Image1 {
    String size,imageUrl,price,name,description,packages,qty,id,desc;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Image1(String name, String description, String packages, String imageUrl, String qty, String id) {
        this.name = name;
        this.description = description;
        this.packages = packages;
        this.imageUrl = imageUrl;
        this.qty = qty;
        this.id = id;
    }
    public Image1(String size, String imageUrl,String price, String desc,String id) {
        this.size =size;
        this.price = price;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.id = id;

    }
}
