module front {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires demo;
    requires jakarta.servlet;
    requires com.google.gson;
    requires okhttp3;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    //requires Java.WebSocket;

    opens front to javafx.fxml, com.google.gson;
    exports front;
    //exports com.example.demo;
}
