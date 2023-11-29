package front;

import javafx.stage.Stage;

import java.io.IOException;


public class Scene_Manager {
    private Stage stage;
    private Login scene1;
    private HelloController scene2;

    public Scene_Manager(Stage stage,Login login,HelloController helloController) throws IOException {
        this.stage = stage;
        this.scene1 = login;
        login.setSceneManager(this);
        this.scene2 = helloController;
        helloController.setSceneManager(this);
    }

    public void showScene1() throws IOException {
        scene1.show();
    }

    public void showScene2() throws IOException {
        scene2.show();
    }

    public Stage getStage() {
        return stage;
    }
}
