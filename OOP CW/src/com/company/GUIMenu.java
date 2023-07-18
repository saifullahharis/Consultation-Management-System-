// Student id 20212163

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class GUIMenu implements  ActionListener{
    JFrame f;
    JLabel title ;
    JButton  viewDoctorsBtn , sortDoctorsBtn , addConsultationBtn, viewConsultationBtn , viewDoctorAvailabilityBtn;
    ArrayList<Doctor> dList;
    ArrayList<Consultation> cList;


    GUIMenu(ArrayList<Doctor> list , ArrayList<Consultation> list1){
        dList = list;
        cList =list1;
        f = new JFrame();
        title = new JLabel("Skin Consultation Center ");
        title.setBounds(300, 20, 600, 30);
        title.setFont(new Font("Serif", Font.PLAIN, 40));

        viewDoctorsBtn = new JButton("View Doctors Details");
        viewDoctorsBtn.setBounds(350,150 , 300 , 50);
        viewDoctorsBtn.addActionListener(this);

        sortDoctorsBtn = new JButton("View Doctors Details in sort order ");
        sortDoctorsBtn.setBounds(350,250 , 300 , 50);
        sortDoctorsBtn.addActionListener(this);

        addConsultationBtn = new JButton(" Add a Consultation ");
        addConsultationBtn.setBounds(350,350 , 300 , 50);
        addConsultationBtn.addActionListener(this);

        viewConsultationBtn = new JButton("View Consultations list");
        viewConsultationBtn.setBounds(350,450,300,50);
        viewConsultationBtn.addActionListener(this);

        viewDoctorAvailabilityBtn = new JButton("View Doctor Availability");
        viewDoctorAvailabilityBtn.setBounds(350,550,300,50);
        viewDoctorAvailabilityBtn.addActionListener(this);



        f.add(title);f.add(viewDoctorsBtn);f.add(sortDoctorsBtn);f.add(addConsultationBtn);f.add(viewConsultationBtn);
        f.add(viewDoctorAvailabilityBtn);
        f.setSize(1000, 800);
        f.setLayout(null);
        f.setVisible(true);
    }


    void doctorsList(ArrayList<Doctor> list ,String title ){
        JFrame frame =new JFrame(title);

        String[][] data =new String[list.size()][6];

        for (int i=0 ; i<list.size();i++){

            Doctor a = list.get(i);
           data[i][0] =a.getName();
           data[i][1] =a.getSurName();
           data[i][2] = a.getDateOfBirth();
           data[i][3] = String.valueOf(a.getMobileNumber());
           data[i][4] =a.getMedicalLicenseNumber();
           data[i][5] =a.getSpecialization();

        }



        String column[]={"Name","Surname","DOB","Mobile Nm","Medical Licence Nm" ,"Specialization"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        frame.add(sp);
        frame.setSize(700,500);
        frame.setVisible(true);

    }

    void consultationList(ArrayList <Consultation> list){
        JFrame frame =new JFrame();

        String[][] data =new String[list.size()][6];

        for (int i =0 ; i < list.size(); i++){
            Consultation consultation =list.get(i);
            Date dateCopy = consultation.getDate();
            Date timeCopy = consultation.getTime();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(dateCopy);
            String time =  new SimpleDateFormat("HH:mm").format(timeCopy);


            data[i][0] =consultation.getPatientName();
            data[i][1] =date;
            data[i][2] = time ;
            data[i][3] = consultation.getDoctorName();
            data[i][4] = consultation.getNotes();
            data[i][5] =String.valueOf(consultation.getCost());

        }

        String column[]={" Patient" , " date ", " time " ," Doctor " ," Notes" , " Cost"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        frame.add(sp);
        frame.setSize(700,500);
        frame.setVisible(true);

    }


    void ViewDoctorAvailability(ArrayList <Consultation> cList,ArrayList<Doctor> dList){
        JFrame frame =new JFrame();

        String[][] data =new String[cList.size()][4];
        int i =0;
        for (Doctor doctor : dList){
            System.out.println();
            for (Consultation consultation : cList){
                if (doctor == consultation.getDoctor()){

                    Date dateCopy = consultation.getDate();
                    Date timeCopy = consultation.getTime();
                    int timePeriod = consultation.getTimePeriod();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(timeCopy);
                    calendar.add(Calendar.HOUR_OF_DAY ,timePeriod);

                    String date = new SimpleDateFormat("yyyy-MM-dd").format(dateCopy);
                    String time =  new SimpleDateFormat("HH:mm").format(timeCopy);
                    String endTime =  new SimpleDateFormat("HH:mm").format(calendar.getTime());

                    data[i][0] = consultation.getDoctorName();
                    data[i][1] =date;
                    data[i][2] = time ;
                    data[i][3] = endTime;


                    i++;

                }
            }
        }




        String column[]={ " Doctor " , " Date ", " Start-time " , "End-time"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        frame.add(sp);
        frame.setSize(700,500);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewDoctorsBtn){
            doctorsList(dList,"Doctors List");
        }else if (e.getSource() ==sortDoctorsBtn){
            ArrayList <Doctor> nwList = new ArrayList<>(dList);
            Comparator<Doctor> comparator = Comparator.comparing(Doctor::getSurName);
            nwList.sort(comparator);
            doctorsList(nwList,"Doctors List in sort order");

        }else if (e.getSource() == addConsultationBtn){
            new GUIAddConsultation(dList,cList);

        } else if (e.getSource() == viewConsultationBtn){
           consultationList(cList);
        }else if (e.getSource() == viewDoctorAvailabilityBtn){
            ViewDoctorAvailability(cList,dList);
        }


    }

}


