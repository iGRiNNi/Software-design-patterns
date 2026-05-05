module org.example.lab_3_8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab_3_8 to javafx.fxml;
    exports org.example.lab_3_8;
}