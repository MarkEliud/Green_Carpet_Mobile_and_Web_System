package com.mk.Green.Driver;

public class Image {

    String cleanerName,  address, customerName, date,  assignee,  code, imageUrl;
    public Image(String cleanerName, String address, String customerName,
                 String date, String assignee, String code,String imageUrl) {
        this.cleanerName = cleanerName;
        this.address = address;
        this.customerName = customerName;
        this.date = date;
        this.assignee = assignee;
        this.code = code;
          this.imageUrl = imageUrl;

    }

    public String getCleanerName() {
        return cleanerName;
    }

    public void setCleanerName(String cleanerName) {
        this.cleanerName = cleanerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
