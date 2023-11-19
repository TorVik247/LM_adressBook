package com.example.demo;

public class Person {
    private String PIP;
    private String Phone;

    public Person(String PIP, String Phone) {
        this.PIP = PIP;
        this.Phone = Phone;
    }

    public String getPIP() {
        return PIP;
    }

    public void setPIP(String PIP) {
        this.PIP = PIP;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
}
