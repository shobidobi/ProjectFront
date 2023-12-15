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

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
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
    @FXML
    private Button SendEmail;
    @FXML
    private CheckBox forgot_password;
    @FXML
    private TextField emailF;

    @FXML
    private Text emailT;


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

    public Login(Scene_Manager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    public void initialize() {
        Log.setMaxSize(408, 544);
    }

    /**
     * @param event show the password
     */
    @FXML
    void ShowHiddenPassword(ActionEvent event) {
        if (ifShow.isSelected()) {
            showPass.setText(passwordF.getText());
            showPass.setVisible(true);
            passwordF.setVisible(false);
        } else {
            passwordF.setText(showPass.getText());
            showPass.setVisible(false);
            passwordF.setVisible(true);
        }
    }

    /**
     * @param event click the sign in button
     * @throws IOException
     * the function are used to check the username and password with the server
     */
    @FXML
    void Sign_in(MouseEvent event) throws IOException {
        String userName = U_N_F.getText();
        String password = passwordF.getText();
        TryApi api = new TryApi();

        if (userName.isEmpty() || password.isEmpty()) {
            System.out.println("empty");
        } else {
            if (!(isValidString(userName) && isValidString(password))) {
                System.out.println("not valid");
            } else {
                if (api.test3(userName, password)) {
                    sceneManager.showScene2();
                }


//                stage2.setScene(scene);
//                stage2.show();
                //helloApplication.stage2.show();
            }

        }
    }

    /**
     * @param input the string to be checked
     * @return true if the string is valid, false if not
     */
    private boolean isValidString(String input) {
        String regex = "^[a-zA-Z0-9]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("valid");
        }
        return matcher.matches();
    }

    /**
     * @param event close the window
     */
    @FXML
    void cancel_form(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void SendEmail(MouseEvent event) throws IOException {
        requestCode(emailF.getText());
        sceneManager.showScene4();
    }

    @FXML
    void showEmailDialog(ActionEvent event) {
        if (forgot_password.isSelected()) {
            emailF.setVisible(true);
            emailT.setVisible(true);
            SendEmail.setVisible(true);
        } else {
            emailF.setVisible(false);
            emailT.setVisible(false);
            SendEmail.setVisible(false);
        }
    }

    @FXML
    void Sign_UP_b(MouseEvent event) throws IOException {
        sceneManager.showScene3();
    }

    private  int requestCode(String email) throws IOException {
        try {
            URL url = new URL("http://localhost:8080/generateCode");  // Replace with your server URL

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Build the request body
            String postData = "email=" + email;
            sceneManager.getScene4().setEmail(email);
            // Write the data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Read the response content
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder responseBody = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                // Print the response status and body
                System.out.println("Response Code: " + responseCode);
                System.out.println("Response Body: " + responseBody.toString());
                sceneManager.getScene4().setCode(responseBody.toString());
                return Integer.parseInt(responseBody.toString());
            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void show () throws IOException {
            FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            fxmlLoader1.setController(this);

            Parent root = fxmlLoader1.load();
            Scene scene = new Scene(root);
            sceneManager.getStage().setTitle("Hello!");
            sceneManager.getStage().setScene(scene);
            sceneManager.getStage().show();
        }
}

