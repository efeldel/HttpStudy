package json;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.*;

public class Main {
    public static void main(String[] args) throws java.net.URISyntaxException, IOException, InterruptedException {
        int n = 5;
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://jservice.io/api/random?count=" + n)).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body().toString();
        System.out.println(jsonString);
        JSONArray array = new JSONArray(jsonString);
        for (int i = 0; i < n; i++){
            JSONObject obj = array.getJSONObject(i);
            String answer = (String) obj.get("answer");
            String question = (String) obj.get("question");
            System.out.println(question + " - " + answer);
        }
    }
}