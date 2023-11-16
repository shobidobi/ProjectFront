package front;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class TryApi {
    public void test(String username, String password) {
        final String SERVER_URL = "http://localhost:8080/login";
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
}
