package victorin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.*;

public class Main {
    public static void main(String[] args) throws java.net.URISyntaxException, IOException, InterruptedException {
        int n = 8;
        int rightCount = 0;
        Scanner sc = new Scanner(System.in);
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://jservice.io/api/random?count=" + n)).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body().toString();
        JSONArray array = new JSONArray(jsonString);
        System.out.println("Викторина:");
        for (int i = 0; i < n; i++){
            JSONObject obj = array.getJSONObject(i);
            System.out.println("Вопрос " + (i + 1) + ":");
            System.out.println((String) obj.get("question"));
            if (sc.nextLine().equalsIgnoreCase((String) obj.get("answer"))) rightCount += 1;
        }
        System.out.println("Правильных ответов: " + rightCount);
    }
}
