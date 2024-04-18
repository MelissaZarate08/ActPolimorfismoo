package com.melissazarate.actpolimorfismo.Models;

import java.util.ArrayList;

public interface StudentRepository {
    void save(Student student);
    void update(Student student);

    ArrayList<Student> readStudents();
}
