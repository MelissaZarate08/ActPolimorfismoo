package com.melissazarate.actpolimorfismo.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.melissazarate.actpolimorfismo.Models.AdminEscolar;
import com.melissazarate.actpolimorfismo.Models.Student;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController {

    @FXML
    private Button CloseButton;

    @FXML
    private Button addButton;

    @FXML
    private ImageView addIcono;

    @FXML
    private TableColumn<Student, String> apellidoColumn;

    @FXML
    private TableColumn<Student, Integer> edadColumn;

    @FXML
    private TableColumn<Student, Integer> idAlumnoColumn;

    @FXML
    private TableColumn<Student, String> nivelAcademicoColumn;

    @FXML
    private TableColumn<Student, String> nombreColumn;

    @FXML
    private TableView<Student> studentTableView;

    @FXML
    private Button updateButton;

    private ObservableList<Student> studentObservableList = FXCollections.observableArrayList();
    private ArrayList<AdminEscolar> adminEscolarArrayList = new ArrayList<>();
    private AdminEscolar adminEscolar;

    public void initialize() {
        adminEscolar = new AdminEscolar();
        idAlumnoColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdAlumno()).asObject());
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        edadColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());
        nivelAcademicoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNivelAcademico()));
        cargarBases();
    }

    private void cargarBases () {
        for (AdminEscolar adminEscolar1 : adminEscolarArrayList) {
            studentObservableList.addAll(adminEscolar1.readFromDatabases());
        }
        studentTableView.setItems(studentObservableList);
    }

    @FXML
    void CloseButton(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/melissazarate/actpolimorfismo/formularioSave-view.fxml"));
            Parent root = loader.load();

            FormularioSaveController controller = loader.getController();
            controller.atributosStudent(null);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

            Student student = controller.getStudent();
            if (student != null) {
                studentObservableList.add(student);
                adminEscolar.saveDatabases(student);
                studentTableView.refresh();
            }
        } catch (IOException e) {
        }
    }

    @FXML
    void addIconoAction(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/melissazarate/actpolimorfismo/formularioSave-view.fxml"));
            Parent root = loader.load();

            FormularioSaveController controller = loader.getController();
            controller.atributosStudent(null);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

            Student student = controller.getStudent();
            if (student != null) {
                studentObservableList.add(student);
                adminEscolar.saveDatabases(student);
                studentTableView.refresh();
            }
        } catch (IOException e) {
        }


    }


    @FXML
    void updateButttonAction(ActionEvent event) {
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null) {
            ErrorAlert("Seleccione un estudiante de la tabla");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/melissazarate/actpolimorfismo/formularioSave-view.fxml"));
                Parent root = loader.load();

                FormularioSaveController controller = loader.getController();
                controller.atributosStudent(selectedStudent);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.showAndWait();

                Student modifiedStudent = controller.getStudent();
                if (modifiedStudent != null) {
                    adminEscolar.updateDataBases(selectedStudent, modifiedStudent);
                    studentTableView.refresh();
                }
            } catch (IOException e) {
            }
        }
    }

    @FXML
    void updateIconoAction(MouseEvent event) {
        Student selectedStudent = studentTableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null) {
            ErrorAlert("Seleccione un estudiante de la tabla");
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/melissazarate/actpolimorfismo/formularioSave-view.fxml"));
                Parent root = loader.load();

                FormularioSaveController controller = loader.getController();
                controller.atributosStudent(selectedStudent);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.showAndWait();

                Student modifiedStudent = controller.getStudent();
                if (modifiedStudent != null) {
                    adminEscolar.updateDataBases(selectedStudent, modifiedStudent);
                    studentTableView.refresh();
                }
            } catch (IOException e) {
            }
        }


    }

    private void ErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}