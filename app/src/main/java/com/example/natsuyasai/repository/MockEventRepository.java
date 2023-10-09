package com.example.natsuyasai.repository;

import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.domain.Year;
import com.example.natsuyasai.domain.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MockEventRepository implements EventRepository {


    @Override
    public String save(Event event) {
        return "qwerty";
    }

    @Override
    public CompletableFuture<Event> findById(String event_id) {
        Event exampleEvent = new Event("駒場祭");
        Message exampleMessage = new Message("今日は人が多いなあ。","スタッフ");
        Map<String,Message> messageMap = new HashMap<>();
        messageMap.put("randomid",exampleMessage);
        Year exampleYear = new Year("2023",messageMap);
        Map<String, Year> yearMap = new HashMap<>();
        yearMap.put("2023", exampleYear);
        exampleEvent.years = yearMap;
        return CompletableFuture.completedFuture(exampleEvent);
    }
}
