package com.example.getrenovator.Models;

import java.util.List;

public class Client extends Person{
    String city;
    List<Client> clientList;

    public Client() {}
    public Client(String first_name, String last_name, String mail, String phone, UserType userType, String city) {
        super(first_name, last_name, mail, phone, userType,city);

    }


    public  List<Client> getClientList(){
        return clientList;

    }



}

