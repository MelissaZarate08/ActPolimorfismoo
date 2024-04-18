package com.melissazarate.actpolimorfismo.Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.melissazarate.actpolimorfismo.Models.AdminEscolar;
import com.melissazarate.actpolimorfismo.Models.Student;
import com.melissazarate.actpolimorfismo.Models.StudentRepository;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CloseButton;

    @FXML
    private Button GuardarButton;

    @FXML
    private Button UpdateButton;

    @FXML
    private TableColumn<Student, String> apellidoColumn;

    @FXML
    private TextField apellidoField;

    @FXML
    private TableColumn<Student, Integer> edadColumn;

    @FXML
    private TextField edadField;

    @FXML
    private TableColumn<Student, Integer> idAlumnoColumn;

    @FXML
    private TextField idAlumnoField;

    @FXML
    private TableColumn<Student, String> nivelAcademicoColumn;

    @FXML
    private TextField nivelAcademicoField;

    @FXML
    private TableColumn<Student, String> nombreColumn;

    @FXML
    private TextField nombreField;

    @FXML
    private TableView<Student> studentTableView;

    private ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
    private ArrayList<StudentRepository> bases = new ArrayList<>();
    private ArrayList<AdminEscolar> adminEscolarArrayList = new ArrayList<>();
    private AdminEscolar adminEscolar;
    private Student selectStudent;

    @FXML
    void CloseButton(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void SaveStudentButton(ActionEvent event) {
        String idAlumno = idAlumnoField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String edad = edadField.getText();
        String nivelAcademico = nivelAcademicoField.getText();

        if (idAlumno.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() || nivelAcademico.isEmpty()) {
            showErrorAlert("Por favor llena todos los campos.");
            return;
        }

        try {
            edad = String.valueOf(Integer.parseInt(String.valueOf(edadField)));
            idAlumno = String.valueOf(Integer.parseInt(String.valueOf(idAlumnoField)));
        }catch (NumberFormatException e) {
            showErrorAlert("Ingrese unicamnete numeros para Id y edad. ");
            return;
        }

        Student newStudent = new Student(idAlumno, nombre, apellido, edad, nivelAcademico);

        if (selectStudent != null) {
            selectStudent.setIdAlumno(Integer.parseInt(idAlumno));
            selectStudent.setNombre(nombre);
            selectStudent.setApellido(apellido);
            selectStudent.setEdad(Integer.parseInt(edad));
            selectStudent.setNivelAcademico(nivelAcademico);
            adminEscolar.updateDataBases(selectStudent, newStudent);
        }else {
            studentObservableList.add(newStudent);
            adminEscolar.saveDatabases(newStudent);
        }
        studentTableView.refresh();
        clearFields();

    }
    private void clearFields(){
        idAlumnoField.clear();
        nombreField.clear();
        apellidoField.clear();
        edadField.clear();
        nivelAcademicoField.clear();
    }

    @FXML
    void UpdateStudentButton(ActionEvent event) {
        selectStudent = studentTableView.getSelectionModel().getSelectedItem();
        if (selectStudent != null){
            idAlumnoField.setText(Integer.toString(selectStudent.getIdAlumno()));
            nombreField.setText(selectStudent.getNombre());
            apellidoField.setText(selectStudent.getApellido());
            edadField.setText(Integer.toString(selectStudent.getEdad()));
            nivelAcademicoField.setText(selectStudent.getNivelAcademico());
        }else {
            showErrorAlert("Seleccione un estudiante de la tabla.");
        }

    }

    @FXML
    void initialize() {
       adminEscolar = new AdminEscolar();
       idAlumnoColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdAlumno()).asObject());
       nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
       apellidoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
       edadColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());
       nivelAcademicoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNivelAcademico()));
       loadBases();
    }

    private void loadBases(){
        for (AdminEscolar adminEscolar1 : adminEscolarArrayList) {
            studentObservableList.addAll(adminEscolar1.readFromDatabases());
        }
        studentTableView.setItems(studentObservableList);
    }

    @FXML
    void agregarAction(ActionEvent event){
        clearFields();
        selectStudent = null;
    }


    private void showErrorAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
