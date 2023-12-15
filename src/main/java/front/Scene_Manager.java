package front;

import javafx.stage.Stage;

import java.io.IOException;


public class Scene_Manager {
    private Stage stage;
    private Login scene1;
    private HelloController scene2;
    private Register scene3;
    private ForgotPassword scene4;

    public Scene_Manager(Stage stage,Login login,HelloController helloController,
                         Register register,
                         ForgotPassword forgotPassword) throws IOException {
        this.stage = stage;
        this.scene1 = login;
        login.setSceneManager(this);
        this.scene2 = helloController;
        helloController.setSceneManager(this);
        this.scene3 = register;
        register.setSceneManager(this);
        this.scene4 = forgotPassword;
        forgotPassword.setSceneManager(this);
    }

    public void showScene1() throws IOException {
        scene1.show();
    }

    public void showScene2() throws IOException {
        scene2.show();
    }
    public void showScene3() throws IOException {
        scene3.show();
    }
    public void showScene4() throws IOException {
        scene4.show();
    }
    public Login getScene1() {
        return scene1;
    }

    public HelloController getScene2() {
        return scene2;
    }

    public Register getScene3() {
        return scene3;
    }

    public ForgotPassword getScene4() {
        return scene4;
    }

    public Stage getStage() {
        return stage;
    }

}
