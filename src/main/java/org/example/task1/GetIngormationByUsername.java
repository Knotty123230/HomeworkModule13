package org.example.task1;

import com.google.gson.reflect.TypeToken;
import org.example.Constants;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.example.Constants.GSON;

public class GetIngormationByUsername {
    public static void getByUserName(String username) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Constants.URI))
                .GET()
                .build();
        try {
            HttpResponse<String> send = Constants.HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            ArrayList<GsonFromPage> list = GSON.newBuilder().setPrettyPrinting().create().fromJson(send.body(), new TypeToken<ArrayList<GsonFromPage>>() {
            }.getType());
            list.stream()
                    .filter(it -> it.getUsername().equals(username))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
