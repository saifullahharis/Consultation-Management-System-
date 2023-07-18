// Student id 20212163

package com.company;

import java.util.ArrayList;

public interface SkinConsultationManager {

     void addDoctors(ArrayList<Doctor> list);
     void removeDoctor(ArrayList <Doctor> list);
     void printDoctors(ArrayList <Doctor> list);
     void sortDoctors(ArrayList <Doctor> list);
     void saveToFile();
     void readFromFile();
     void generateReport();

}
