
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonObject;



public class HuggingFaceClient {


    private static final String API_KEY = "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";

    public static String getResponseFromHuggingFace(String userMessage) {
        try {

            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("inputs", userMessage);


            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api-inference.huggingface.co/models/gpt2")) // Модель GPT-2
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString(), StandardCharsets.UTF_8))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                return response.body();
            } else {
                return "Error: " + response.statusCode();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while contacting Hugging Face.";
        }
    }
}
