package com.mk.Green.Activity.Adapters;
public class Image {

    private String name,packages,qty,id,investatus;

    public String getInvestatus() {
        return investatus;
    }

    public void setInvestatus(String investatus) {
        this.investatus = investatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    private String fullname;
    private String date;
    private String description;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String size,price,desc;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private String email;
    private String imageUrl,code,amount,address,customerName,status,fprice;

    public String getFprice() {
        return fprice;
    }

    public void setFprice(String fprice) {
        this.fprice = fprice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
String items;

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }
    public Image(String name, String date, String description, String imageUrl ,
                 String status, String customerName, String code, String address,
                 String amount, String fprice, String email) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.imageUrl = imageUrl;

        this.status = status;
        this.customerName = customerName;
        this.code = code;
        this.amount = amount;
        this.address = address;
        this.fprice = fprice;
        this.email = email;
        //this.items = items;
    }
    public Image(String name, String date, String description, String imageUrl ,
                 String status, String customerName, String code, String address,
                 String amount, String fprice, String email, String items) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.imageUrl = imageUrl;

        this.status = status;
        this.customerName = customerName;
        this.code = code;
        this.amount = amount;
        this.address = address;
        this.fprice = fprice;
        this.email = email;
        this.items = items;
        //2500+1500....
    }
    String pck;

    public String getPck() {
        return pck;
    }

    public void setPck(String pck) {
        this.pck = pck;
    }

    public Image(String name, String pck, String amount, String description ,
                 String status, String code, String fprice,String id,String investatus) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.code = code;
        this.amount = amount;
        this.fprice = fprice;
        this.pck = pck;
        this.id = id;
        this.investatus = investatus;
    }
    public Image(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;

    }
//    public Image(String name, String description,String packages, String imageUrl,String qty,String id) {
//        this.name = name;
//        this.description = description;
//        this.packages = packages;
//        this.imageUrl = imageUrl;
//        this.qty = qty;
//        this.id = id;
//    }
    public Image(String size, String imageUrl,String price, String desc,String id, String category,String x) {
        this.size =size;
        this.price = price;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.id = id;
        this.category = category;
    }


    String cleanerName,assignee,category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCleanerName() {
        return cleanerName;
    }

    public void setCleanerName(String cleanerName) {
        this.cleanerName = cleanerName;
    }

    public Image(String cleanerName, String address, String customerName, String date, String assignee, String code) {
        this.cleanerName = cleanerName;
        this.address = address;
        this.customerName = customerName;
        this.date = date;
        this.assignee = assignee;
        this.code = code;
      //  this.imageUrl = imageUrl;

    }
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }


    public String getImageUrl() {
        return imageUrl;
    }

}
