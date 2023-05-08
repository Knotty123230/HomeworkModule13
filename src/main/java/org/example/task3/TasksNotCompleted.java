package org.example.task3;

import com.google.gson.reflect.TypeToken;
import org.example.Constants;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TasksNotCompleted {
    public static ArrayList<UserTasks> getTasks(int id) {
        String url = "https://jsonplaceholder.typicode.com/users/" + id + "/todos";
        ArrayList<UserTasks> list;
        try {
            String text = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get()
                    .text();
            list = Constants.GSON.fromJson(text, new TypeToken<ArrayList<UserTasks>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static void printTasks(int id) {
        List<Boolean> collect = getTasks(id).stream()
                .map(it -> it.isCompleted())
                .collect(Collectors.toList());
        for (Boolean s : collect) {
            if (!s) {
                System.out.println("Completed:" + " " + s);
            }
        }
    }
}
