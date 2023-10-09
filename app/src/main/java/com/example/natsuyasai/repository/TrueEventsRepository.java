package com.example.natsuyasai.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.domain.Events;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class TrueEventsRepository implements EventsRepository{

    public void getAndSetEvents() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://natsuyasai-e77f6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // URIは良さそう。
        DatabaseReference ref = database.getReference("events");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Events.sharedEvents = (Map<String, Event>) snapshot.getValue();
                System.out.println("get and set event");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Events.sharedEvents = (Map<String, Event>) snapshot.getValue();
                System.out.println("get and set event");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Events.sharedEvents = (Map<String, Event>) snapshot.getValue();
                System.out.println("get and set event");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Events.sharedEvents = (Map<String, Event>) snapshot.getValue();
                System.out.println("get and set event");
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){
                System.out.println("could not get events");
            }
        });
    }
}
