// Student id 20212163

package com.company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    static Scanner input = new Scanner(System.in);
    static ArrayList<Doctor> doctorsList = new ArrayList<>(10);
    static ArrayList<Consultation> consultationList = new ArrayList<>();
    static File file1 = new File("file.txt");
    static int counter = 0;



    public static void main(String[] args) {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        while (true) {
            System.out.println();
            System.out.println("""
                    1 : Add a new doctor\s
                    2 : Delete a doctor\s
                    3 : Print the list of the doctors\s
                    4 : Print the list of the doctors in sort order\s
                    5 : Save data to a file\s
                    6 : Load data from file\s
                    7 : Load GUI\s
                    8 : Book a Consultation\s
                    9 : print  Consultation details \s
                    10 : Delete a Consultation  \s
                    0 : Quit""");
            System.out.print("Please enter your choice : ");
            String selection = input.next();
            System.out.println();

            if (selection.equals("1")) {
                manager.addDoctors(doctorsList);
            } else if (selection.equals("2")) {
                manager.removeDoctor(doctorsList);
            } else if (selection.equals("3")) {
                manager.printDoctors(doctorsList);
            } else if (selection.equals("4")) {
                manager.sortDoctors(doctorsList);
            } else if (selection.equals("5")) {
                manager.saveToFile();
            } else if (selection.equals("6")) {
                manager.readFromFile();
            } else if (selection.equals("7")) {
                new GUIMenu(doctorsList,consultationList);
            } else if (selection.equals("8")) {
                manager.bookConsultations(consultationList);
            } else if (selection.equals("9")) {
                manager.generateReport();
            } else if (selection.equals("10")) {
                manager.deleteConsultation(consultationList);
            } else if (selection.equals("0")) {
                break;
            } else {
                System.out.println(" Wrong input ");
            }
        }
    }


    /*Adding doctors to ArrayList*/
    @Override
    public void addDoctors(ArrayList<Doctor> dList) {

        while (true) {
            if (counter < 10 && counter >= 0) {
                System.out.println("Enter following details of doctor");
                System.out.print("Enter the  name :");
                String name = input.next();
                System.out.print("Enter the Sure name :");
                String SureName = input.next();
                System.out.print("Enter the date of birth (yyyy-mm-dd) :");
                String dob1 = input.next();
                SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
                Date dob;
                try {
                    dob = dateInput.parse(dob1);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    continue;
                }

                int mobileNumber;
                try {
                    System.out.print("Enter the mobile number :");
                    String number = input.next();
                    mobileNumber = Integer.parseInt(number);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    continue;
                }
                System.out.print("Enter the medical licence number  :");
                String medicalLicenceNumber = input.next();
                System.out.print("Enter the specialisation  :");
                String specialization = input.next();
                System.out.println();

                try {
                    Doctor doctor = new Doctor(name, SureName, dob, mobileNumber, medicalLicenceNumber, specialization);
                    dList.add(doctor);
                } catch (Exception e) {
                    continue;
                }

                if (dList.get(counter) != null) {
                    System.out.println("Doctor added to list  successfully . \n");
                    counter++;
                    break;
                }
            } else {
                System.out.println("Doctors are full \n");
                break;
            }
        }
    }

    /*Remove a Doctor form ArrayList*/
    @Override
    public void removeDoctor(ArrayList<Doctor> dList) {
        for (Doctor doctor :dList){
            System.out.println("Dr." + doctor.getName() + " " + doctor.getSurName() + " ,  "+
                    " medical licence number - " + doctor.getMedicalLicenseNumber());
        }
        System.out.print("Enter the medical licence number to remove a doctor :");
        try {
            String selection = input.next();
            for (Doctor doctor : dList){
                if (selection.equals(doctor.getMedicalLicenseNumber())){
                    dList.remove(doctor);
                    System.out.println(doctor + "   Removed successfully \n"
                            + "Now "+ dList.size() + " Doctors in the center");
                    counter --;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Wrong input");
        }

    }

    /*Print doctors list in console */
    @Override
    public void printDoctors(ArrayList<Doctor> dList) {
        if (dList.isEmpty()) {
            System.out.println(" Doctor list is empty \n");
        }else {
            System.out.println(" Doctors List");
            int i = 1;
            for (Doctor a : dList) {
                //System.out.println(list[i].getName() +" "+list[i].getSurName());
                if (a.getSurName() != null) {
                    System.out.println(i + ". " + a.toString());
                }
                i++;
            }
        }
        System.out.println();
    }

    /*Print doctors list in sort order  */
    @Override
    public void sortDoctors(ArrayList<Doctor> dList) {
        ArrayList<Doctor> nwList = new ArrayList<>(dList);
        Comparator<Doctor> comparator = Comparator.comparing(Doctor::getSurName);
        Collections.sort(nwList, comparator);
        printDoctors(nwList);
    }

    /*Save Doctors list and Consultation list to file  */
    @Override
    public void saveToFile() {
        try {
            ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(file1));
            oos1.writeObject(doctorsList);
            oos1.writeObject(consultationList);

            oos1.close();
            System.out.println("Successfully stored all data  to file .");
            System.out.println(" ");



        } catch (Exception e) {
            System.out.println("Error in creating file");
            System.out.println(" ");
        }
    }


    /*Read Doctors list and Consultation list from file  */
    @Override
    public void readFromFile() {
        try {
            if (file1.isFile()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file1));
                doctorsList = (ArrayList<Doctor>) ois.readObject();
                counter = calculateCounter(doctorsList);
                consultationList = (ArrayList<Consultation>) ois.readObject();
                ois.close();
                printDoctors(doctorsList);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.toString());
        }
    }

    /*Print Consultation list to console */
    @Override
    public void generateReport() {
        System.out.println();
        int i =1 ;
        for (Consultation a : consultationList) {
            if (a != null) {
                System.out.println(i + ". " + a);
            }
            i++;
        }
    }

    public static int calculateCounter(ArrayList<Doctor> list1) {
        int counter1 = 0;
        for (Doctor doctor : list1) {
            counter1++;
        }
        return counter1;
    }

    /*To create a consultation and save to consultation list*/
    public void bookConsultations(ArrayList<Consultation> list) {
        while (true) {
            if (doctorsList.size() == 0){
                System.out.println("Sorry Doctors are busy");
                break;
            }else {
                System.out.print("Enter the patients name :");
                String name = input.next();
                System.out.print("Enter the patients Sure name :");
                String sureName = input.next();
                System.out.print("Enter the patients date of birth (yyyy-mm-dd) :");
                String dob1 = input.next();

                SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
                Date dob;
                try {
                    dob = dateInput.parse(dob1);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    continue;
                }

                int mobileNumber;
                try {
                    System.out.print("Enter the mobile number :");
                    mobileNumber = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    continue;
                }

                System.out.print("Enter the patients id  :");
                String id = input.next();


                System.out.print("Enter the date for consultation (yyyy-mm-dd) : ");
                String date1 = input.next();
                Date date;
                try {
                    date = dateInput.parse(date1);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    continue;
                }


                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                System.out.print("Enter the start time of consultation  (HH:mm):");
                String startTime1 = input.next();
                Date startTime;
                try {
                    startTime = timeFormat.parse(startTime1);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    continue;
                }

                System.out.print("how many hours do you want to book :");
                int timePeriod =  0;
                try{
                   Double timePeriod1 =input.nextDouble();
                    timePeriod = (int) Math.round(timePeriod1);
                }catch (Exception e){
                    System.out.println("Invalid input");
                }

                printDoctors(doctorsList);
                int doctorSelection;
                Doctor doctor;
                try {
                    System.out.print("Select a Doctor : ");
                    doctorSelection = input.nextInt();
                    doctor =doctorsList.get(doctorSelection-1);
                    System.out.println("Dr.  "+ doctor.getSurName() + "  selected");
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    continue;
                }


               doctor =  randomDoctorSelection(doctor,date,startTime,timePeriod);



                int cost;
                if (list.size() == 0){
                    cost = 15 * timePeriod;
                }else{
                    cost = 25 * timePeriod;
                }

                System.out.print("Enter some notes about  consultation :");
                String notes = input.next();



                try {
                    Consultation consultation;
                    Patient patient = new Patient(name ,sureName,dob,mobileNumber,id);

                    consultation =new Consultation(date,startTime,timePeriod ,cost,notes,patient,doctor);
                    consultationList.add(consultation);

                    System.out.println("Successfully added consultation");
                    System.out.println(consultation);

                } catch (Exception e) {
                    System.out.println("Some thing Went wrong ,Try again");
                    continue;
                }
                break;

            }
        }
    }

    /*to update consultation list ,when create a consultation in GUI  */
    public void updateList(ArrayList<Consultation> list){
        consultationList =list;
    }

    /*Delete a doctor from doctors list*/
    public Doctor randomDoctorSelection(Doctor a , Date date, Date time , int hours){
        for(Consultation consultation : consultationList){
            Doctor doctor = consultation.getDoctor();
            Date cDate =consultation.getDate();
            Date cTime =consultation.getTime();

            if (a == doctor){
                if (date.compareTo(cDate) == 0){
                    for (int i =0 ; i < hours +1 ;i++){
                       if (time.getHours() == cTime.getHours()+i){
                           Random generator = new Random();
                           int randomIndex = generator.nextInt(doctorsList.size());
                           a = doctorsList.get(randomIndex);
                           randomDoctorSelection(a,date,time,hours);
                           System.out.println("Sorry selected doctor is busy at that time , so Dr ."+
                                   a.getName() + " "+ a.getSurName());
                       }
                    }
                }
            }
        }
        return a;
    }

    /*Delete a consultation from consultation list*/
    public void deleteConsultation(ArrayList<Consultation> cList){
        int i =1;
        for (Consultation consultation:cList){
            System.out.println(i + ". " +consultation);
            i++;
        }
        System.out.println("Select a consultation to remove");
        try {
            int selection = input.nextInt();
            Consultation c = cList.get(selection - 1);
            cList.remove(selection - 1);
            counter--;
            System.out.println(c + "removed successfully \n");

        } catch (Exception e) {
            System.out.println("Wrong input");
        }

    }

}

