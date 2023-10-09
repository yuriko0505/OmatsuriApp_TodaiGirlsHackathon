package com.example.natsuyasai;

import android.app.Application;

import com.example.natsuyasai.domain.Events;
import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {
    public AppContainer appContainer = new AppContainer();

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        appContainer.initFirebaseDatabase();
    }
}
