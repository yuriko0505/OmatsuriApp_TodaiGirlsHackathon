package com.example.natsuyasai.viewmodels;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import com.example.natsuyasai.MyApplication;
import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.domain.Year;
import com.example.natsuyasai.repository.EventRepository;
import com.example.natsuyasai.state.EventUiState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class EventViewModel extends ViewModel {

    private final EventRepository eventRepository;

    private final MutableLiveData<EventUiState> uiState =
            new MutableLiveData<>(new EventUiState(new ArrayList<>()));

    public EventViewModel(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public LiveData<EventUiState> getUiState() {
        return uiState;
    }

    public void load(final String eventId) {
        Log.i("ID1",eventId);
        CompletableFuture<Event> eventFuture = eventRepository.findById(eventId);
        eventFuture.whenComplete((result, exception) -> {
            // todo: error handling
            final List<Year> years = new ArrayList<>(result.years.values());
            uiState.postValue(new EventUiState(years));
        });
    }

    public static final ViewModelInitializer<EventViewModel> initializer = new ViewModelInitializer<>(
            EventViewModel.class,
            creationExtras -> {

                MyApplication app = (MyApplication) creationExtras.get(APPLICATION_KEY);
                assert app != null;

                return new EventViewModel(app.appContainer.eventRepository);
            }
    );
    
}
