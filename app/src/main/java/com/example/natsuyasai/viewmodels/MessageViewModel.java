package com.example.natsuyasai.viewmodels;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.natsuyasai.MyApplication;
import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.domain.Message;
import com.example.natsuyasai.repository.EventRepository;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class MessageViewModel extends ViewModel {

    private final EventRepository eventRepository;



    public MessageViewModel(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }



    public static final ViewModelInitializer<MessageViewModel> initializer = new ViewModelInitializer<>(
            MessageViewModel.class,
            creationExtras -> {
                MyApplication app = (MyApplication) creationExtras.get(APPLICATION_KEY);
                assert app != null;

                return new MessageViewModel(app.appContainer.eventRepository);
            }
    );


    public CompletableFuture<Map<String , Message>> getMessages(String eventId, String year) {
        final CompletableFuture<Map<String , Message>> future = new CompletableFuture<>();
        CompletableFuture<Event> event = eventRepository.findById(eventId);
        event.whenComplete((result, exception) -> {
            Map<String , Message> messages =  result.years.get(year).messages;
            future.complete(messages);
        });

        return future;
    }

//    public Event saveYear(String eventId) {
//        Event event = eventRepository.findById(eventId);
//        if (!(event.years == null)){
//            if (!event.years.containsKey(yearName)) {
//
//                final Map<String, Message> messages = new HashMap<>();
//                final Year year = new Year(this.yearName, messages);
//                event.years.put(yearName, year);
//            }
////        }
//
//        eventRepository.save(event);
//        return event;
//
//    }
}
