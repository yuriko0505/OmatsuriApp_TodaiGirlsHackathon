package com.example.natsuyasai.viewmodels;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.natsuyasai.MyApplication;
import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.repository.EventRepository;

public class NewEventViewModel extends ViewModel {

    private final EventRepository eventRepository;

    public NewEventViewModel(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private String eventName = "default";

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public static final ViewModelInitializer<NewEventViewModel> initializer = new ViewModelInitializer<>(
            NewEventViewModel.class,
            creationExtras -> {
                MyApplication app = (MyApplication) creationExtras.get(APPLICATION_KEY);
                assert app != null;

                return new NewEventViewModel(app.appContainer.eventRepository);
            }
    );

    public Event saveEvent() {
        final Event event = new Event(this.eventName);
        eventRepository.save(event);
        return event;

    }
}
