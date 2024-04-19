package com.melissazarate.actpolimorfismo.Controllers;


import com.melissazarate.actpolimorfismo.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FormularioSaveController {

    @FXML
    private Button GuardarButton;

    @FXML
    private ImageView saveIcono;

    @FXML
    private TextField apellidoField;

    @FXML
    private TextField edadField;

    @FXML
    private TextField idAlumnoField;

    @FXML
    private TextField nivelAcademicoField;

    @FXML
    private TextField nombreField;

    private Student student;
    private AdminEscolar adminEscolar;

    public Student getStudent() {
        return student;
    }

    public void initialize() {
        adminEscolar = new AdminEscolar();
    }

    public void atributosStudent (Student student) {
        this.student = student;

        if (student != null) {
            idAlumnoField.setText(String.valueOf(student.getIdAlumno()));
            nombreField.setText(student.getNombre());
            apellidoField.setText(student.getApellido());
            edadField.setText(String.valueOf(student.getEdad()));
            nivelAcademicoField.setText(student.getNivelAcademico());
        }
    }

    @FXML
    void SaveStudentButton(ActionEvent event) {
        String idAlumnoText = idAlumnoField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String edadText = edadField.getText();
        String nivelAcademico = nivelAcademicoField.getText();

        if (idAlumnoText.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edadText.isEmpty() || nivelAcademico.isEmpty()) {
            ErrorAlert("Algunos campos están vacíos");
            return;
        }

        int idAlumno;
        int edad;

        try {
            idAlumno = Integer.parseInt(idAlumnoText);
            edad = Integer.parseInt(edadText);
        } catch (NumberFormatException e) {
            return;
        }

        Student newStudent = new Student(idAlumno, nombre, apellido, edad, nivelAcademico);

        if (student != null) {
            student.setNivelAcademico(nivelAcademico);
            student.setNombre(nombre);
            student.setApellido(apellido);
            student.setEdad(edad);
            student.setNivelAcademico(nivelAcademico);
        } else {
            student = newStudent;
            adminEscolar.saveDatabases(student);
        }
        closeWindow();
    }

    @FXML
    void saveStudentIcono(MouseEvent event) {
        String idAlumnoText = idAlumnoField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String edadText = edadField.getText();
        String nivelAcademico = nivelAcademicoField.getText();

        if (idAlumnoText.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edadText.isEmpty() || nivelAcademico.isEmpty()) {
            ErrorAlert("Algunos campos están vacíos");
            return;
        }

        int idAlumno;
        int edad;

        try {
            idAlumno = Integer.parseInt(idAlumnoText);
            edad = Integer.parseInt(edadText);
        } catch (NumberFormatException e) {
            return;
        }

        Student newStudent = new Student(idAlumno, nombre, apellido, edad, nivelAcademico);

        if (student != null) {
            student.setNivelAcademico(nivelAcademico);
            student.setNombre(nombre);
            student.setApellido(apellido);
            student.setEdad(edad);
            student.setNivelAcademico(nivelAcademico);
        } else {
            student = newStudent;
            adminEscolar.saveDatabases(student);
        }
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) GuardarButton.getScene().getWindow();
        stage.close();
    }

    private void ErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}