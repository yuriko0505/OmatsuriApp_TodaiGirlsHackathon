package com.example.natsuyasai.domain;

import android.util.Log;

import java.util.Date;
import java.util.Map;

public class Message {

    public String content;
    public Date date;
    public String user_profession;

    public Message(String content, String user_profession) {
        this.content = content;
        this.date = new Date();
        this.user_profession = user_profession;
    }

    public static Message from(Object value) {
        final Map<String, Object> firebaseData = (Map<String, Object>) value;
        Message message = new Message(
                (String) firebaseData.get("content"),
                (String) firebaseData.get("user_profession")
        );
        Log.i("timestamp", (String) firebaseData.get("timestamp"));
        return message;
    }
}

