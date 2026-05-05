module org.example.lab_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab_4 to javafx.fxml;
    exports org.example.lab_4;
}