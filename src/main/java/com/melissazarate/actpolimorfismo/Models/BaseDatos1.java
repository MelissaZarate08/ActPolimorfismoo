package com.melissazarate.actpolimorfismo.Models;

import java.util.ArrayList;

public class BaseDatos1 implements StudentRepository {

    private ArrayList<Student> estudiante = new ArrayList<>();

    @Override
    public void save(Student student) {
        estudiante.add(student);
    }
    
    @Override
    public void update(Student student){
       for (Student e : estudiante){
           if (e.getIdAlumno()==student.getIdAlumno()){
               e.setNombre(student.getNombre());
               e.setApellido(student.getApellido());
               e.setNivelAcademico(student.getNivelAcademico());
               e.setEdad(student.getEdad());

           }
       }
    }
    @Override
    public ArrayList<Student> leerStudents(){
        return new ArrayList<>(estudiante);
    }
}

