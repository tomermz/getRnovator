package com.example.getrenovator.Models;

public class Person {

    String first_name;
    String last_name;
    String mail;
    String password;
    String phone;
    UserType userType;
    String City;

    public Person(String first_name, String last_name, String mail, String phone, UserType userType, String City) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.phone = phone;
        this.userType = userType;
        this.City  = City;

    }

    public Person(){
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }
}
