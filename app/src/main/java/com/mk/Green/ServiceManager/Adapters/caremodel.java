package com.mk.Green.ServiceManager.Adapters;

public class caremodel {
    private  String name, MobileNo,email,phone,specialization,total;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public caremodel(String name, String mobileNo, String email) {
        this.name = name;
        MobileNo = mobileNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public  caremodel(String name, String phone, String specialization, String email, String total){
        this.name=name;
        this.total=total;
        this.phone=phone;
        this.email=email;
        this.specialization=specialization;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
