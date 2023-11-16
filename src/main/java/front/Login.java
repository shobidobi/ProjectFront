package front;

import com.example.demo.Check;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Login  {

    @FXML
    private Button SignIn;

    @FXML
    private Button SignUp;

    @FXML
    private TextField U_N_F;



    @FXML
    private CheckBox ifShow;

    @FXML
    private PasswordField passwordF;

    @FXML
    private TextField showPass;
    public Login() {}
    Check check=new Check();
    @FXML
    public void initialize() {

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
    void Sign_in(MouseEvent event) {
        String userName=U_N_F.getText();
        String password=passwordF.getText();
        //check.tryLogin(userName,password);
        System.out.println("try login");
        //check.isValid(userName,password);
    }



}
