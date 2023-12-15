package front;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ForgotPassword {

    @FXML
    private TextField codeF;

    @FXML
    private TextField confirmF;

    @FXML
    private TextField passwordF;

    @FXML
    private Button resetB;
    private Scene_Manager sceneManager;
    private static final String BASE_URL = "http://localhost:8080/api/"; // Replace with your server URL
    private String email;
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    private String code;
    public void setSceneManager(Scene_Manager sceneManager) {
        this.sceneManager = sceneManager;
    }
    @FXML
    void reset(MouseEvent event) {
        String code = codeF.getText();
        System.out.println(this.code);
        String password = passwordF.getText();
        String confirm = confirmF.getText();
        if (code.equals(this.code) && password.equals(confirm)){
            System.out.println("Reset password successfully");
            resetPassword(this.email,password);
        }
        else {
            System.out.println("Reset password failed");
        }
    }
    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forgot_Password.fxml"));
        fxmlLoader.setController(this);
        Scene scene = new Scene(fxmlLoader.load());
        sceneManager.getStage().setScene(scene);
        sceneManager.getStage().show();
    }
    private void resetPassword(String email, String newPassword) {
        try {
            URL url = new URL("http://localhost:8080/api/resetPassword");  // Replace with your server URL

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Build the request body
            String postData = "password=" + newPassword+"&emailr=" + email;
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

    private static String mapToJson(Map<String, String> data) {
        return "";
    }
}
