package com.example.natsuyasai.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import com.example.natsuyasai.MyApplication;
import com.example.natsuyasai.repository.EventRepository;
import com.example.natsuyasai.state.MainUiState;

public class MainViewModel extends ViewModel {

    private final EventRepository eventRepository;

    private final MutableLiveData<MainUiState> uiState =
            new MutableLiveData<>(new MainUiState(null));

    public MainViewModel(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public LiveData<MainUiState> getUiState() {
        return uiState;
    }

    public static final ViewModelInitializer<MainViewModel> initializer = new ViewModelInitializer<>(
            MainViewModel.class,
            creationExtras -> {

                MyApplication app = (MyApplication) creationExtras.get(APPLICATION_KEY);
                assert app != null;

                return new MainViewModel(app.appContainer.eventRepository);
            }
    );
    
}
