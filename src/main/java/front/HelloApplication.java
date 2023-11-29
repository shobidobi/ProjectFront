package front;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override

    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

//        Scene scene = new Scene(fxmlLoader1.load());
//        stage.setTitle("Hello!");
////        stage.setMaxHeight(544);
////        stage.setMaxWidth(408);
//        stage.setScene(scene);
        Login login = new Login();
        HelloController helloController = new HelloController();
        Scene_Manager sceneManager = new Scene_Manager(stage, login, helloController);
        sceneManager.showScene1();

        //stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}