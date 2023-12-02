package com.mk.Green.Driver.Adapters;

public class flightModel {
   String duration,datetime,status,destination, price;
   String code, name,fullname,email,date,cancelreason,arrivaldate,dest,planeid;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(String startlocation) {
        this.startlocation = startlocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlaneid() {
        return planeid;
    }

    public void setPlaneid(String planeid) {
        this.planeid = planeid;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(String arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public flightModel(int id, String name, String email) {
        this.name=name;
        this.email=email;
        this.id=id;
    }
    String sender;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public flightModel(String sender) {
        this.sender=sender;

    }
    String attendant, copilot;

    public String getAttendant() {
        return attendant;
    }

    public void setAttendant(String attendant) {
        this.attendant = attendant;
    }

    public String getCopilot() {
        return copilot;
    }

    public void setCopilot(String copilot) {
        this.copilot = copilot;
    }

    public flightModel(String planeid, String dest, String date, String status, String attendant, String copilot) {
        this.planeid=planeid;
        this.dest=dest;
        this.date=date;
        this.status=status;
        this.attendant=attendant;
        this.copilot=copilot;
    }

    public flightModel(String planeid, String dest, String date, String status, String arrivaldate) {
        this.planeID=planeid;
        this.destination=dest;
        this.date=date;
        this.status=status;
        this.arrivaldate=arrivaldate;
    }

    public String getCancelreason() {
        return cancelreason;
    }

    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    int id;

    public flightModel(String duration, String datetime, String status, String destination, String price, int id, String cancelreason) {
        this.duration = duration;
        this.datetime = datetime;
        this.status = status;
        this.destination = destination;
        this.price = price;
        this.id = id;
        this.cancelreason = cancelreason;
    }
    String planeID,seats;

    public String getPlaneID() {
        return planeID;
    }

    public void setPlaneID(String planeID) {
        this.planeID = planeID;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
   String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public flightModel(String name, String destination, String email, String price, String code,
                       String date, String phone, String status, int id, String planeID) {

        this.status = status;
        this.phone=phone;
        this.destination = destination;
        this.price = price;
        this.id = id;
        this.name=name;
        this.code=code;
        this.email=email;
        this.date=date;
        this.planeID=planeID;
    }
    public flightModel(String name, String destination, String email, String price, String code, int id, String date, String status) {

        this.status = status;
        this.destination = destination;
        this.price = price;
        this.id = id;
        this.name=name;
        this.code=code;
        this.email=email;
        this.date=date;
    }
String amount,seat,startlocation,type;

    public flightModel(String name, String destination, String email, String price, String code,
                       String date, String phone, String status, int id, String amount, String startlocation, String seat, String type) {
        this.type = type;
        this.status = status;
        this.phone=phone;
        this.destination = destination;
        this.price = price;
        this.id = id;
        this.name=name;
        this.code=code;
        this.email=email;
        this.date=date;
        this.amount=amount;
        this.seat=seat;
        this.startlocation=startlocation;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
