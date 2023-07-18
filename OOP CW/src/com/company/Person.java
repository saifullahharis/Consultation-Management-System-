// Student id 20212163

package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Person implements Serializable {
    private String name;
    private String surName;
    private Date dateOfBirth;
    private int mobileNumber;


    public Person(String name, String surName,Date dateOfBirth, int mobileNumber) {
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getDateOfBirth() {

        return new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);
    }

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}