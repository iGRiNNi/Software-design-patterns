module org.example.lab_2_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lab_2_3 to javafx.fxml;
    exports org.example.lab_2_3;
}