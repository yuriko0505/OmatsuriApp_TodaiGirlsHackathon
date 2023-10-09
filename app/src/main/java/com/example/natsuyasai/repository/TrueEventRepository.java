package com.example.natsuyasai.repository;

import android.util.Log;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.natsuyasai.domain.Event;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TrueEventRepository implements EventRepository {

    private final DatabaseReference eventsRef;

    public TrueEventRepository(DatabaseReference eventsRef) {
        this.eventsRef = eventsRef;
    }

    @Override
    //event_id を返す。Databaseのupdate
    public String save(Event event) {
        DatabaseReference pushedEventRef = eventsRef.push();
        // eventを保存する。
        pushedEventRef.setValue(event);
        // event_idを取得する。
        String eventId = pushedEventRef.getKey();
        event.setEventId(eventId);
        return eventId;
    }

    @Override
    /**
     * may return null if event is not found by id
     */
    public CompletableFuture<Event> findById(String eventId) {
        Log.i("Event", eventId);
        final CompletableFuture<Event> future = new CompletableFuture<>();
        eventsRef.child(eventId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("firebase", String.valueOf(task.getResult().getValue()));
                future.complete(Event.from(eventId, task.getResult().getValue()));
            } else {
                Log.e("firebase", "Error getting data", task.getException());
                future.cancel(true);
            }
        });

        return future;
    }
}
