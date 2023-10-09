package com.example.natsuyasai.viewmodels;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.natsuyasai.MyApplication;
import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.domain.Message;
import com.example.natsuyasai.domain.Year;
import com.example.natsuyasai.repository.EventRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class NewYearViewModel extends ViewModel {

    private final EventRepository eventRepository;

    public NewYearViewModel(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private String yearName = "";

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getYearName() {
        return yearName;
    }

    public static final ViewModelInitializer<NewYearViewModel> initializer = new ViewModelInitializer<>(
            NewYearViewModel.class,
            creationExtras -> {
                MyApplication app = (MyApplication) creationExtras.get(APPLICATION_KEY);
                assert app != null;

                return new NewYearViewModel(app.appContainer.eventRepository);
            }
    );

    public CompletableFuture<Event> saveYear(String eventId) {
        Log.i("EventId", eventId);
        final CompletableFuture<Event> result = new CompletableFuture<>();
        final CompletableFuture<Event> eventFuture = eventRepository.findById(eventId);
        eventFuture.whenComplete((event, exception) -> {
            if (!event.years.containsKey(yearName)) {
                final Map<String, Message> messages = new HashMap<>();
                final Year year = new Year(this.yearName, messages);
                event.years.put(yearName, year);
            }
            eventRepository.save(event);
            result.complete(event);
        });
        return result;
    }
}
