module com.melissazarate.actpolimorfismo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.melissazarate.actpolimorfismo to javafx.fxml;
    exports com.melissazarate.actpolimorfismo;
    exports com.melissazarate.actpolimorfismo.Controllers;
    opens com.melissazarate.actpolimorfismo.Controllers to javafx.fxml;
}