// Student id 20212163

package com.company;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Doctor extends Person  {
    private String medicalLicenseNumber;
    private String specialization;


    public Doctor(String name, String surName, Date dateOfBirth, int mobileNumber, String medicalLicenseNumber, String specialization) {
        super(name, surName, dateOfBirth, mobileNumber);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }

    // Getters
    public String getMedicalLicenseNumber(){
        return medicalLicenseNumber;
    }
    public String getSpecialization(){
        return specialization;
    }
    // Setters
    public void setMedicalLicenseNumber(String medicalLicenseNumber){
        this.medicalLicenseNumber = medicalLicenseNumber;
    }
    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }
    public void display(){
        System.out.println("Name : "+getName()+" Surname : "+getSurName()+" Date Of Birth : "+getDateOfBirth()+" Mobile Number : "+getMobileNumber());
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "  Name : "+ getName()+  "  " +
                "SurName : "+ getSurName()+  "  " +
                "Date of birth :"+ getDateOfBirth()+  "  " +
                "Mobile number  :"+ getMobileNumber()+  "  " +
                "medicalLicenseNumber : " + medicalLicenseNumber + "  " +
                " specialization : " + specialization + "  " +
                '}';
    }
}
