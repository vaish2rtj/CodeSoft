import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String API_KEY =loadApiKey();
    private static String loadApiKey(){
        try(BufferedReader br=new BufferedReader(new FileReader("apikey.txt"))){
            return br.readLine().trim().replaceAll("[^a-zA-Z0-9]","");
        }catch(Exception e){
            System.out.println("Failed to load API key");
            return "";
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Currency Converter ===");

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  

        System.out.print("From currency (e.g. USD): ");
        String from = scanner.nextLine().toUpperCase();

        System.out.print("To currency (e.g. INR): ");
        String to = scanner.nextLine().toUpperCase();

        try {
            double rate = getExchangeRate(from, to);
            double result = rate * amount;

            System.out.printf("%.2f %s = %.2f %s\n", amount, from, result, to);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

    public static double getExchangeRate(String from, String to) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from;

        // ✅ Use URI to avoid deprecated URL constructor
        URI uri = new URI(urlStr);
        URL url = uri.toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("HTTP Error: " + conn.getResponseCode());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder responseJson = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseJson.append(line);
        }

        reader.close();

        // Manually extract exchange rate using string search
        String searchKey = "\"" + to + "\":";
        int index = responseJson.indexOf(searchKey);
        if (index == -1) {
            throw new Exception("Currency code not found: " + to);
        }

        int start = index + searchKey.length();
        int end = responseJson.indexOf(",", start);
        if (end == -1) {
            end = responseJson.indexOf("}", start);  // if it's the last in the list
        }

        String rateStr = responseJson.substring(start, end).trim();
        return Double.parseDouble(rateStr);
    }
}
