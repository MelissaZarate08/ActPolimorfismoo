package com.melissazarate.actpolimorfismo.Models;

public class Student {
    private int idAlumno;
    private String nombre;
    private String apellido;
    private int edad;
    private String nivelAcademico;

    public Student(String idAlumno, String nombre, String apellido, String edad, String nivelAcademico) {
        this.idAlumno = Integer.parseInt(idAlumno);
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = Integer.parseInt(edad);
        this.nivelAcademico = nivelAcademico;

    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }
    @Override
    public String toString(){
        return "Student{" +
                "ID='" + idAlumno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad='" + edad + '\'' +
                ", nivel academico=" + nivelAcademico +
                '}';
    }
}
