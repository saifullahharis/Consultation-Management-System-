// Student id 20212163

package com.company;

import java.time.LocalDate;
import java.util.Date;

public class Patient extends Person {
    private String id;

    public Patient(String name, String surName, Date dateOfBirth, int mobileNumber, String id) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Patient{ Name "+ super.getName() +
                " SureName "+ super.getSurName() +
                " Date "+ super.getDateOfBirth() +
                " No "  + super.getMobileNumber() +
                " id " + id + "  " + '}';
    }
}
