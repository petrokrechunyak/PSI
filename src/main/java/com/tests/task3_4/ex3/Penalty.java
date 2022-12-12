package com.tests.task3_4.ex3;


public class Penalty {
    private String date_time;
    private String first_name;
    private String last_name;
    private String type;
    private double fine_amount;

    public Penalty() {
    }

    public Penalty(String date_time, String first_name, String last_name, String type, double fine_amount) {
        this.date_time = date_time;
        this.first_name = first_name;
        this.last_name = last_name;
        this.type = type;
        this.fine_amount = fine_amount;
    }

    @Override
    public String toString() {
        return "Penalty{" +
                "date_time='" + date_time + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", type=" + type +
                ", fine_amount=" + fine_amount +
                '}';
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFine_amount() {
        return fine_amount;
    }

    public void setFine_amount(double fine_amount) {
        this.fine_amount = fine_amount;
    }
}
