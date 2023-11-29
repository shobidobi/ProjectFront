package front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login  {
    @FXML
    private AnchorPane Log;
    @FXML
    private Text Password_text;

    @FXML
    private Text Register_text;

    @FXML
    private Button SignIn;

    @FXML
    private Button SignUp;

    @FXML
    private Text Title;

    @FXML
    private TextField U_N_F;

    @FXML
    private Text User_name_text;

    @FXML
    private ImageView background;

    @FXML
    private CheckBox ifShow;

    @FXML
    private PasswordField passwordF;

    @FXML
    private TextField showPass;
    @FXML
    private Button Close;
    Stage stage;

    public void setSceneManager(Scene_Manager sceneManager) {
        this.sceneManager = sceneManager;
    }

    Scene_Manager sceneManager;

    public Login() {
        System.out.println("one constructor");
    }

    public Login(Stage stage2) {
        this.stage = stage2;
    }

    public Login( Scene_Manager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        Log.setMaxSize(408,544);

    }
    @FXML
    void ShowHiddenPassword(ActionEvent event) {
        if(ifShow.isSelected()){
            showPass.setText(passwordF.getText());
            showPass.setVisible(true);
            passwordF.setVisible(false);
        }
        else{
            passwordF.setText(showPass.getText());
            showPass.setVisible(false);
            passwordF.setVisible(true);
        }
    }
    @FXML
    void Sign_in(MouseEvent event) throws IOException {
        String userName=U_N_F.getText();
        String password=passwordF.getText();
        TryApi api=new TryApi();

        if (userName.isEmpty()||password.isEmpty()){
            System.out.println("empty");
        }
        else {
            if (!(isValidString(userName)&&isValidString(password))){
                System.out.println("not valid");
            }
            else {
                if (api.test3(userName,password)){
                    sceneManager.showScene2();
                }


//                stage2.setScene(scene);
//                stage2.show();
                //helloApplication.stage2.show();
            }

        }
    }
    private boolean isValidString(String input) {
        String regex = "^[a-zA-Z0-9]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            System.out.println("valid");
        }
        return matcher.matches();
    }
    @FXML
    void cancel_form(MouseEvent event) {
        System.exit(0);
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        fxmlLoader1.setController(this);

        Parent root = fxmlLoader1.load();
        Scene scene = new Scene(root);
        //FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
//        Scene scene = new Scene(fxmlLoader1.load());
        sceneManager.getStage().setTitle("Hello!");
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().show();
    }
}
