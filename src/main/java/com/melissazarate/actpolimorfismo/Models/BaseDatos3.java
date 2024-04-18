package com.melissazarate.actpolimorfismo.Models;

import java.util.ArrayList;

public class BaseDatos3 implements StudentRepository {

    private ArrayList<Student> student3 = new ArrayList<>();
    @Override
    public void save(Student student) {
        student3.add(student);
    }
    @Override
    public void update(Student student){
        for (Student e : student3){
            if (e.getIdAlumno()==student.getIdAlumno()){
                e.setNombre(student.getNombre());
                e.setApellido(student.getApellido());
                e.setNivelAcademico(student.getNivelAcademico());
                e.setEdad(student.getEdad());

            }
        }
    }
    @Override
    public ArrayList<Student> readStudents(){
        return new ArrayList<>(student3);
    }
}

