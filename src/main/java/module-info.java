module front {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires demo;
    requires jakarta.servlet;
    requires com.google.gson;
    requires okhttp;

    opens front to javafx.fxml;
    exports front;
    //exports com.example.demo;
}
