package com.example.getrenovator.Models;


import java.text.DecimalFormat;

public class Renovator extends Person  {
    String Rating;
    public int count = 1;
    public double sum = 0;

    public Renovator(String first_name, String last_name, String mail, String phone, UserType userType, String rating, String city) {
        super(first_name, last_name, mail, phone, userType, city);
        this.Rating = rating;
    }

    public Renovator(String first_name, String last_name, String mail, String phone, UserType userType,String city) {
        super(first_name, last_name, mail, phone, userType,city);

    }

    public Renovator() {}

    public  String getRating() {
        return Rating;
    }

    public  void setRating(String rating) {
        this.Rating = rating;
        setSum(Double.parseDouble(rating));
    }


    public  void calRating(double rate){

        int countTemp = getCount();
        double sum = getSum();
        rate = rate+ sum;
        double i = rate/countTemp;
        DecimalFormat df = new DecimalFormat("#.##");
        setRating(df.format(i));

    }
    public void setCount()
    {
        this.count =this.count + 1;
    }
    public  int getCount()
    {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = this.sum+sum;
    }

}


