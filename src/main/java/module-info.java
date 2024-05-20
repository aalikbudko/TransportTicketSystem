module com.example.metro {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;
    requires junit;


    opens com.example.metro to javafx.fxml;
    exports com.example;
    opens com.example to javafx.fxml;
}