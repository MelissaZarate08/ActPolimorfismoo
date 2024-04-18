package com.melissazarate.actpolimorfismo.Models;
import java.util.ArrayList;

public class AdminEscolar {
    private ArrayList<StudentRepository> bases = new ArrayList<>();
    private BaseDatos1 baseDatos1;
    private BaseDatos2 baseDatos2;
    private BaseDatos3 baseDatos3;

    public AdminEscolar(){
        baseDatos1 = new BaseDatos1();
        baseDatos2 = new BaseDatos2();
        baseDatos3 = new BaseDatos3();

        bases.add(baseDatos1);
        bases.add(baseDatos2);
        bases.add(baseDatos3);
    }
    public void saveDatabases(Student student){
        for (StudentRepository base : bases){
            base.save(student);
        }
    }
    public void updateDataBases(Student modifStudent, Student originStudent){
        for (StudentRepository base : bases){
            base.update(modifStudent);
        }
    }
    public ArrayList<Student> readFromDatabases(){
        ArrayList<Student> allStudents = new ArrayList<>();
        for (StudentRepository base : bases){
            allStudents.addAll(base.readStudents());
        }
        return allStudents;
    }
}
