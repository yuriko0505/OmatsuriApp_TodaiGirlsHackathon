package com.example.natsuyasai.repository;

import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.domain.Message;

public interface MessageRepository {

    // Define methods here
    void save(String event_id, int year, Message message);

}
