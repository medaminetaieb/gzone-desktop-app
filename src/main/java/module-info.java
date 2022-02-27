module com.example.gzone {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.base;


    opens com.example.gzone to javafx.fxml;
    exports com.example.gzone;
}