package front;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public class TryApi {
    final String SERVER_URL = "http://localhost:8080/login";

    public void test(String username, String password) {
        UserDetails user = new UserDetails();
        user.setUsername(username);
        user.setPassword(password);

        // Convert UserDetails object to JSON
        Gson gson = new Gson();
        String json = gson.toJson(user);

        // Send a POST request to the server
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
            Request request = new Request.Builder().url(SERVER_URL).post(requestBody).build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void test2(String username, String password){
        UserDetails user = new UserDetails();
        user.setUsername(username);
        user.setPassword(password);

        Gson gson = new Gson();
        String json = gson.toJson(user);

        // Send a POST request to the server
        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create( MediaType.parse("application/json"),json);
            Request request = new Request.Builder().url(SERVER_URL).post(requestBody).build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean test3(String username, String password){
        String loginUrl = "http://localhost:8080/login1";

        // Encode the credentials into the query string
        String queryParams = String.format("?username=%s&password=%s",
                username, password);

        String getUrl = loginUrl + queryParams;

        try {
            // Create a URL object
            URL url = new URL(getUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up the request method and headers
            connection.setRequestMethod("POST");

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
                if (responseBody.toString().equals("Login successful")){
                    System.out.println("..........");
                    return true;
                }
                else{
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean registerApi(String user_name,String password,String email) {
        try {
            URL url = new URL("http://localhost:8080/api/users/register");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Build the request body
            String postData = String.format("username=%s&email=%s&password=%s",
                    URLEncoder.encode(user_name, "UTF-8"),
                    URLEncoder.encode(email, "UTF-8"),
                    URLEncoder.encode(password, "UTF-8"));

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

                return responseBody.toString().equals("User registered successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
//    public boolean test4(String username, String password) throws IOException {
//        String url = "http://localhost:8080/api/login";
//
//        UserDetails user = new UserDetails();
//        user.setUsername(username);
//        user.setPassword(password);
//
//        Gson gson = new Gson();
//        String json = gson.toJson(user);
//
//        // Send a POST request to the server
//        try {
//            OkHttpClient client = new OkHttpClient();
//            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
//            Request request = new Request.Builder().url(SERVER_URL).post(requestBody).build();
//
//            Response response = client.newCall(request).execute();
//
//            if (response.isSuccessful()) {
//                System.out.println("Login successful");
//            } else {
//                System.out.println("Login failed: " + response.body().string());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}

