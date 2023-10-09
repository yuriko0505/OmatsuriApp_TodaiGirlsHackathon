package com.example.natsuyasai.ui;

import static com.example.natsuyasai.ui.ActivityConstants.EVENT_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.natsuyasai.MyApplication;
import com.example.natsuyasai.R;
import com.example.natsuyasai.domain.Year;
import com.example.natsuyasai.state.EventUiState;
import com.example.natsuyasai.viewmodels.EventViewModel;
import com.google.firebase.FirebaseApp;

import java.util.List;
import java.util.stream.Collectors;

public class EventActivity extends AppCompatActivity {
    private String eventId;
    private static final String[] years = {"2022", "2021", "2020"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final Intent intent = getIntent();
        eventId = intent.hasExtra(EVENT_ID)
            ? intent.getStringExtra(EVENT_ID)
            : getEventIdFromData(intent);

        Log.i("EventId", eventId);

        final EventViewModel viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(EventViewModel.initializer)
        ).get(EventViewModel.class);

        viewModel.load(eventId);
        viewModel.getUiState().observe(this, eventUiState -> {
            // Update the table view.
            List<String> yearsList = eventUiState.getYears()
                    .stream()
                    .map(y -> String.valueOf(y.year))
                    .collect(Collectors.toList());

            ListView listView = findViewById(R.id.years_list_view);
            ListAdapter arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, yearsList);
            listView.setAdapter(arrayAdapter);
        });

        ListView listView = findViewById(R.id.years_list_view);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, years);
        listView.setAdapter(arrayAdapter);

        //リスト項目が選択された時のイベントを追加
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), MessagesActivity.class);
                // clickされたpositionのyearのID
                String selectedYear = years[position];
                // インテントにセット
                intent.putExtra("Year", selectedYear);
                intent.putExtra(EVENT_ID, eventId);

                // Activity をスイッチする
                startActivity(intent);
                overridePendingTransition(R.anim.enter_right, R.anim.exit_left);
            }
        });
    }

    // How to test:
    // $ ${HOME}/Library/Android/sdk/platform-tools/adb shell am start -W -a android.intent.action.VIEW -d "natsuyasai://events/testtest" com.example.natsuyasai
    private String getEventIdFromData(Intent intent) {
        final Uri data = intent.getData();
        final String[] paths = data.toString().split("/");
        final String eventId = paths[paths.length - 1];
        return eventId;
    }

    public void createNewYear(final View view){
        final Intent intent = new Intent(this, NewYearActivity.class);
        intent.putExtra(EVENT_ID, eventId);
        startActivity(intent);


        overridePendingTransition(R.anim.enter_right, R.anim.exit_left);
    }


}