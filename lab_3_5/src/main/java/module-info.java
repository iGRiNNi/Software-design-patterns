module org.example.lab_3_5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab_3_5 to javafx.fxml;
    exports org.example.lab_3_5;
}