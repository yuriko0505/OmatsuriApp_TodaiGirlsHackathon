package com.example.natsuyasai;

import com.example.natsuyasai.repository.EventRepository;
import com.example.natsuyasai.repository.EventsRepository;
import com.example.natsuyasai.repository.MessageRepository;
import com.example.natsuyasai.repository.TrueEventRepository;
import com.example.natsuyasai.repository.TrueEventsRepository;
import com.example.natsuyasai.repository.TrueMessageRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppContainer {

    public final EventsRepository eventsRepository = new TrueEventsRepository();

    public EventRepository eventRepository;
    public final MessageRepository messageRepository = new TrueMessageRepository();

    public FirebaseDatabase database;
    // URIは良さそう。
    public DatabaseReference eventsRef;


    public void initFirebaseDatabase() {
        database = FirebaseDatabase.getInstance("https://natsuyasai-e77f6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        eventsRef = database.getReference("events");
        eventRepository = new TrueEventRepository(eventsRef);
    }
}
