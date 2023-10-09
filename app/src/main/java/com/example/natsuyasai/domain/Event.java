package com.example.natsuyasai.domain;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Event {
    public String eventId;
    public String name;
    // TODO: use generics
    public Map<String, Year> years;


    public Event(String name){
        this.name = name;
        this.years = new HashMap<>();
    }

    public static Event from(final String eventId, final Object value) {
        final Map<String, Object> firebaseData = (Map<String, Object>) value;
        final Event event = new Event((String) firebaseData.get("name"));
        event.eventId = eventId;


        final Map<String, Object> years = firebaseData.containsKey("years")
                ? (Map<String, Object>) firebaseData.get("years")
                : new HashMap<>();
        event.years = years.entrySet()
                .stream()
                .map(e -> new Pair<>(e.getKey(), Year.from(e.getValue())))
                .collect(Collectors.toMap(
                        p -> p.first,
                        p -> p.second
                ));
        return event;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
