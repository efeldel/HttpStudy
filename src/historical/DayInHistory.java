package historical;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DayInHistory {
    private static int day;
    private static int month;
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите день месяца (1-31):");
                day = sc.nextInt();
                if (day <= 0 || day > 31) throw new IllegalArgumentException("Должно быть число от 1 до 31");
                else break;
            } catch (InputMismatchException | IllegalArgumentException ex) {
                System.out.println("Должно быть число от 1 до 31");
            }
        }
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите месяц (1-12):");
                month = sc.nextInt();
                if (month <= 0 || month > 12) throw new IllegalArgumentException("Должно быть число от 1 до 12");
                else break;
            } catch (InputMismatchException | IllegalArgumentException ex) {
                System.out.println("Должно быть число от 1 до 12");
            }
        }
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://numbersapi.com/" + month + "/" + day + "/date")).GET().build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        String text = response.body();
        System.out.println(text);
    }
}
