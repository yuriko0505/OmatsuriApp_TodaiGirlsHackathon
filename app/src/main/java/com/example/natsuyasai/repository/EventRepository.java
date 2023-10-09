package com.example.natsuyasai.repository;

import com.example.natsuyasai.domain.Event;

import java.util.concurrent.CompletableFuture;

public interface EventRepository {

    // Define methods here
    // 新しいeventを保存し、event_idを返す。
    String save(Event event);

    CompletableFuture<Event> findById(String event_id);
}
