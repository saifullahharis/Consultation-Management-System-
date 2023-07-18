// Student id 20212163

package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GUIAddConsultation implements ActionListener {
    WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

    JFrame f;
    JLabel title ,subTitle1,nameLbl , surNameLbl , dOBLbl , mobileNumberLbl , idLbl ,dateLbl, StartTimeLbl,
            timePeriodLbl, timePeriodLbl1,notesLbl, selectDoctor ,costLbl;

    JTextField nameTF , surNameTF , dObTF , mobileNumberTF , idTF ,dateTF, startTimeTF, timePeriodTF , costTF;

    /** For clear Text-fields **/
    ArrayList <JTextField> textFields;

    JTextArea notesTF;
    JButton bookBtn ,clearBtn;
    JComboBox cb;
    ArrayList<Doctor> dList;
    ArrayList<Consultation> cList;

    String name; String surName; String dObTxt; Date dOb; int mobileNm; String id ; Date date; String dateTxt;
    Date startTime ;String timePeriodTxt ;int timePeriod ; String notes ;Doctor doctor ;double cost;
    String successMessage = "Consultation added to list Successfully ,\n";

    Patient patient;
    Consultation consultation;


    GUIAddConsultation(ArrayList<Doctor> list, ArrayList<Consultation> list1) {
        dList =list;
        cList = list1;

        f = new JFrame();
        title = new JLabel("Add Consultation ");
        title.setBounds(300, 20, 600, 30);
        title.setFont(new Font("Serif", Font.PLAIN, 40));

        subTitle1 = new JLabel("Patient's details");
        subTitle1.setBounds(100,80,200,30);
        subTitle1.setFont(new Font("Serif", Font.PLAIN, 20));

        nameLbl = new JLabel(" Name");
        nameLbl.setBounds(30,150,200,30);
        nameTF = new JTextField();
        nameTF.setBounds(200,150,300,30);

        surNameLbl = new JLabel(" Surname");
        surNameLbl.setBounds(30,200,200,30);
        surNameTF = new JTextField();
        surNameTF.setBounds(200,200,300,30);

        dOBLbl = new JLabel(" Date of birth (yyyy-mm-dd)");
        dOBLbl.setBounds(30,250,200,30);
        dObTF = new JTextField();
        dObTF.setBounds(200,250,300,30);

        mobileNumberLbl = new JLabel(" Mobile number");
        mobileNumberLbl.setBounds(30,300,200,30);
        mobileNumberTF = new JTextField();
        mobileNumberTF.setBounds(200,300,300,30);

        idLbl = new JLabel(" Id");
        idLbl.setBounds(30,350,200,30);
        idTF = new JTextField();
        idTF.setBounds(200,350,300,30);

        dateLbl = new JLabel(" Date (yyyy-mm-dd)");
        dateLbl.setBounds(30,400,200,30);
        dateTF = new JTextField();
        dateTF.setBounds(200,400,300,30);

        StartTimeLbl = new JLabel("Start time   (HH:mm)");
        StartTimeLbl.setBounds(30,450,200,30);
        startTimeTF = new JTextField();
        startTimeTF.setBounds(200,450,300,30);

        timePeriodLbl = new JLabel("Time period");
        timePeriodLbl.setBounds(30,500,200,30);
        timePeriodTF = new JTextField();
        timePeriodTF.setBounds(200,500,150,30);
        timePeriodLbl1 = new JLabel(" - Hours (in numbers)");
        timePeriodLbl1.setBounds(350,500,150,30);

        notesLbl = new JLabel(" Notes");
        notesLbl.setBounds(30,550,200,80);
        notesTF = new JTextArea();
        notesTF.setBounds(200,550,300,80);


        selectDoctor = new JLabel("Select a doctor ");
        selectDoctor.setBounds(550,80,200,30);


        costLbl = new JLabel("Enter the cost : ");
        costLbl.setBounds(550,300,200,30);
        costTF = new JTextField();
        costTF.setBounds(650,300,300,30);


        clearBtn =new JButton("clear");
        clearBtn.setBounds(100,680,100,50);
        clearBtn.addActionListener(this);

        bookBtn =new JButton("Book");
        bookBtn.setBounds(800,680,100,50);
        bookBtn.addActionListener(this);

        String [] names = new String[this.dList.size()];

        for (int i = 0; i < this.dList.size(); i++){
            Doctor a = this.dList.get(i);
            String name = "Dr. "+a.getName() +" " +a.getSurName();
            names[i] =name;
        }


        cb=new JComboBox(names);
        cb.setBounds(700, 80,200,30);


        f.add(title);f.add(subTitle1);f.add(nameLbl);f.add(nameLbl);f.add(nameTF);f.add(surNameLbl);
        f.add(surNameTF);f.add(dOBLbl);f.add(dObTF);f.add(mobileNumberLbl);f.add(mobileNumberTF);f.add(idLbl);
        f.add(idTF);f.add(dateLbl);f.add(dateTF);f.add(StartTimeLbl);f.add(timePeriodLbl);
        f.add(timePeriodLbl1);f.add(timePeriodTF);f.add(startTimeTF);f.add(notesLbl);f.add(notesTF);
        f.add(selectDoctor);f.add(cb);f.add(costLbl); f.add(costTF);

        f.add(bookBtn);f.add(clearBtn);

        f.setSize(1000, 800);
        f.setLayout(null);
        f.setVisible(true);
    }


    public void successfulMessage(){
        JFrame frame =new JFrame();
        JOptionPane.showMessageDialog(frame, successMessage);
    }

    public void errorMessage(){
        JFrame frame =new JFrame();

        JOptionPane.showMessageDialog(frame,"Please check entered details " ,"Error" ,JOptionPane.YES_OPTION);
    }




    public void clearTF(){
        nameTF.setText("");  surNameTF.setText("");  dObTF.setText("");  mobileNumberTF.setText("");
        idTF.setText("");dateTF.setText(""); startTimeTF.setText(""); timePeriodTF.setText("");
        costTF.setText("");
    }





    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bookBtn){
            SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
            try{
                name = nameTF.getText();
                surName = surNameTF.getText();
                dObTxt = dObTF.getText();
                dOb =dateInput.parse(dObTxt);
                String mobileNumberTF1 =mobileNumberTF.getText();
                mobileNm = Integer.parseInt(mobileNumberTF1);
                id = idTF.getText();

                dateTxt =dateTF.getText();
                date = dateInput.parse(dateTxt);

                DateFormat timeFormat = new SimpleDateFormat("HH:mm");
                String startTm = startTimeTF.getText();
                startTime = timeFormat.parse(startTm);

                timePeriodTxt = timePeriodTF.getText();
                double timePeriod1 =Double.parseDouble(timePeriodTxt);
                timePeriod = (int) Math.round(timePeriod1);

                if (timePeriod == 0) {
                    timePeriod = 1;  }

                notes =notesTF.getText();

                if (cList.size() == 0){
                    cost =  (15 * timePeriod);
                }else{
                    cost =  (25 * timePeriod);
                }
                String costText = costTF.getText();
                Double doubleCost = Double.parseDouble(costText);

                if (doubleCost !=cost ) {
                    successMessage = successMessage + "Entered cost is wrong , new Cost =" + cost;
                }

                /**  Creating patient **/
                patient =new Patient(name,surName,dOb,mobileNm,id);
                System.out.println( patient);

                int selection = cb.getSelectedIndex();
                doctor = dList.get(selection);

                doctor = manager.randomDoctorSelection(doctor,date,startTime,timePeriod);

                /**  Creating a consultation **/
                consultation =new Consultation(date,startTime,timePeriod ,cost,notes,patient,doctor);

                cList.add(consultation);

                /**  Update the consultation list **/
                manager.updateList(cList);
                clearTF();
                successfulMessage();

            }catch (Exception ignored){
                errorMessage();
            }
        }else if (e.getSource() == clearBtn){
            clearTF();
        }

    }

}




