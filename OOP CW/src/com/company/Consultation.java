// Student id 20212163


package com.company;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Consultation implements Serializable {
    private Date date;
    private Date time;
    private double cost;
    private String notes;
    private  Patient patient;
    private  Doctor doctor;
    private int timePeriod ;

    public Consultation(Date date, Date time,int timePeriod, double cost, String notes , Patient patient , Doctor doctor ){
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.notes = notes;
        this.patient =patient;
        this.doctor =doctor;
        this.timePeriod =timePeriod ;
    }




// Getters

    public int getTimePeriod() {return timePeriod;}
    public Date getDate(){
        return date;
    }
    public Date getTime(){return time;}
    public double getCost(){
        return cost;
    }
    public String getNotes(){
        return notes;
    }
    public Doctor getDoctor() {return doctor;}
    public Patient getPatient() {return patient;}
    public String getDoctorName(){
        String doctorName = doctor.getName()+ "  " +doctor.getSurName();
        return  doctorName;
    }
    public  String getPatientName(){
        String patientName = patient.getName()+ "  " +patient.getSurName();
        return  patientName;
    }

    // Setters
    public void setDate(Date date){
        this.date = date;
    }
    public void setDoctor(Doctor doctor) {this.doctor = doctor;}
    public void setPatient(Patient patient) {this.patient = patient;}
    public void setTime(Date time){
        this.time = time;
    }
    public void setCost(double cost){
        this.cost = cost;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "date=" + new SimpleDateFormat("yyyy-MM-dd").format(date) +
                ", time='" + new SimpleDateFormat("HH:mm").format(time) +
                ", cost=" + cost +
                ", notes='" + notes +
                ", patient=" +  patient.getName() + "  " +patient.getSurName() +
                ", doctor=" + "Dr. "+doctor.getName()+ "  " +doctor.getSurName() +
                '}';
    }
}


//