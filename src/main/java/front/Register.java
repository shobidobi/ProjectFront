package front;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class Register {
    @FXML
    private Button Close_b;

    @FXML
    private TextField Email_f;

    @FXML
    private TextField Password_f;

    @FXML
    private Button Register_b;

    @FXML
    private Button Sign_IN;

    @FXML
    private Text Title_form;

    @FXML
    private TextField UserName_f;
    Scene_Manager sceneManager;
    public void setSceneManager(Scene_Manager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @FXML
    void close(javafx.scene.input.MouseEvent mouseEvent) {
        System.exit(0);
    }
    @FXML
    void registers(javafx.scene.input.MouseEvent mouseEvent) {
        String username = UserName_f.getText();
        String email = Email_f.getText();
        String password = Password_f.getText();
        //TryApi tryApi=new TryApi();
        registerApi(username,password,email);
    }

    @FXML
    void signIN(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        sceneManager.showScene1();
    }
    public boolean registerApi(String user_name,String password,String email) {
        try {
            URL url = new URL("http://localhost:8080/api/users/register");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String postData = String.format("username=%s&email=%s&password=%s",
                    URLEncoder.encode(user_name, "UTF-8"),
                    URLEncoder.encode(email, "UTF-8"),
                    URLEncoder.encode(password, "UTF-8"));

            // Write the data to the output stream
            try (OutputStream os = connection.getOutputStream()) {
                try (OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
                    osw.write(postData);
                }
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

                return responseBody.toString().equals("User registered successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
        public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().show();
    }


}
