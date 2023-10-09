package com.example.natsuyasai.ui;

import static com.example.natsuyasai.ui.ActivityConstants.EVENT_ID;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.natsuyasai.R;
import com.example.natsuyasai.domain.Message;
import com.example.natsuyasai.domain.Message;
import com.example.natsuyasai.viewmodels.EventViewModel;
import com.example.natsuyasai.viewmodels.MessageViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class MessagesActivity extends AppCompatActivity {

    private static final String[] messages_2022 = {
            "Mercury", "Venus", "Earth", "Mars", "Jupyter", "Saturn", "Uranus", "Neptune", "Pluto",
            "Mercury", "Venus", "Earth", "Mars", "Jupyter", "Saturn", "Uranus", "Neptune", "Pluto"
    };
    private static final String[] messages_2021 = {
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    };

    private String eventId;
    private String year;

    private Map<String, Message> messages;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);


        //アクションバーのオブジェクトを取得
        ActionBar actionBar = getSupportActionBar();
        //アクションバーに「戻るボタン」を追加
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.messages_list_view);

        Intent intent = getIntent();
        this.eventId = intent.getStringExtra(ActivityConstants.EVENT_ID);
        String selectedYear = intent.getStringExtra(ActivityConstants.YEAR);
        this.year = selectedYear;

        Log.i("Year", selectedYear);
        if (selectedYear.equals("2022")) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages_2022);
            listView.setAdapter(arrayAdapter);
        } else if (selectedYear.equals("2021")) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages_2021);
            listView.setAdapter(arrayAdapter);
        }
        final String eventId = intent.getStringExtra(EVENT_ID);

        final MessageViewModel viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(MessageViewModel.initializer)
        ).get(MessageViewModel.class);


        List<CustomListData> objects = new ArrayList<CustomListData>();


        //messagesをデータから取得
        final CompletableFuture<Map<String, Message>> future = viewModel.getMessages(eventId,selectedYear);
        future.whenComplete((result, exception) -> {
                    for (Message message : result.values()) {
                        CustomListData item = new CustomListData();
                        item.setMessageData(message.content);
                        item.setProfessionData(message.user_profession);
                        objects.add(item);
                    }
                    this.messages = result;
                });

        CustomAdapter customAdapter = new CustomAdapter(this, 0, objects);

        listView.setAdapter(customAdapter);


//        if (selectedYear.equals("2022")) {
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages_2022);
//            listView.setAdapter(arrayAdapter);
//        } else if (selectedYear.equals("2021")) {
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages_2021);
//            listView.setAdapter(arrayAdapter);
//        }

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem menuButton){
        //戻り値の変数を定義。trueを初期値として代入
        boolean result = true;
        //選択されたメニューボタンのIDを取得(今回は戻るボタンのみのため条件分岐不要だが、追加したときを想定し記載)
        int buttonId = menuButton.getItemId();
        //IDで条件分岐(戻るボタンが押されたときの命令以外省略可)
        switch(buttonId){
            //戻るボタンが押されたとき
            case android.R.id.home:
                //画面を終了させる
            {
                Intent intent = new Intent(getApplication(), EventActivity.class);
                intent.putExtra(ActivityConstants.EVENT_ID, this.eventId);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_left, R.anim.exit_right);
            };
            break;
            //それ以外の時
            default:
                result = super.onOptionsItemSelected(menuButton);
                break;
        }
        return result;
    }
}