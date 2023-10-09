package com.example.natsuyasai.domain;

import android.util.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Year {
    public String year;
    public Map<String,Message> messages;

    public Year(String year, Map<String,Message> messages) {
        this.year = year;
        this.messages = messages;
    }


    public static Year from(Object value) {
        final Map<String, Object> firebaseData = (Map<String, Object>) value;
        final String year = (String) firebaseData.get("year");
        final Map<String, Object> messagesInDb = firebaseData.containsKey("messages")
                ? (Map<String, Object>) firebaseData.get("messages")
                : new HashMap<>();
        final Map<String, Message> messages = messagesInDb.entrySet()
                .stream()
                .map(e -> new Pair<>(e.getKey(), Message.from(e.getValue())))
                .collect(Collectors.toMap(
                        p -> p.first,
                        p -> p.second
                ));
        return new Year(year, messages);
    }
}
