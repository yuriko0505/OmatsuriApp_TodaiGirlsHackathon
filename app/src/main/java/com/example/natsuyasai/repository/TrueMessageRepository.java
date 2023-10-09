package com.example.natsuyasai.repository;

import com.example.natsuyasai.domain.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrueMessageRepository implements MessageRepository{
    @Override
    public void save(String event_id, int year, Message message) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://natsuyasai-e77f6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // URLの値は良さそう。
        DatabaseReference messagesRef = database.getReference("events/"+event_id+"/years/"+year+"/messages");
        messagesRef.push().setValue(message);
    }
}
