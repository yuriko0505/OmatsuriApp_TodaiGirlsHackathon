package com.example.natsuyasai.domain;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.repository.TrueEventRepository;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Events {
    public static Map<String, Event> sharedEvents = new HashMap<>();
    static {
        Events.getAndSetEvents();
    }
    public static void getAndSetEvents() {
        Log.i("STATUS", "get and set event func called");

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://natsuyasai-e77f6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // URIは良さそう。
        DatabaseReference ref = database.getReference("events");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Events.sharedEvents =  (Map<String, Event>) snapshot.getValue();
                Log.i("STATUS","onChildAdded");

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Events.sharedEvents = (Map<String, Event>) snapshot.getValue();
                Log.i("STATUS", "onChildChanged");


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Events.sharedEvents = (Map<String, Event>) snapshot.getValue();
                Log.i("STATUS", "onChildRemoved");
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Events.sharedEvents = (Map<String, Event>) snapshot.getValue();
                Log.i("STATUS", "onChildMoved");

                Log.i("STATUS", "star"+Events.sharedEvents.toString());

            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){
                Log.i("ERROR","could not get event");
            }
        });
    }
}

