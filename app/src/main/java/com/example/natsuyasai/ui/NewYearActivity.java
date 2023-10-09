package com.example.natsuyasai.ui;

import static com.example.natsuyasai.ui.ActivityConstants.EVENT_ID;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.natsuyasai.R;
import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.viewmodels.NewEventViewModel;
import com.example.natsuyasai.viewmodels.NewYearViewModel;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class NewYearActivity extends AppCompatActivity {
    private Boolean finishNaming = false;

    private String eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_year);

        final Intent intent = getIntent();
        eventId = intent.getStringExtra(EVENT_ID);
        Log.i("Event", eventId);

        //アクションバーのオブジェクトを取得
        ActionBar actionBar = getSupportActionBar();
        //アクションバーに「戻るボタン」を追加


        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);
        final NewYearViewModel viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(NewYearViewModel.initializer)
        ).get(NewYearViewModel.class);



        Button createYearButton = findViewById(R.id.button_create_year);
        // lambda式
        createYearButton.setOnClickListener(v -> {
            if(finishNaming){
                final CompletableFuture<Event> future = viewModel.saveYear(eventId);
                future.whenComplete((event, exception) -> {
                    final Intent nextIntent = new Intent(getApplication(), MessagesActivity.class);
                    nextIntent.putExtra(ActivityConstants.EVENT_ID, event.eventId);
                    nextIntent.putExtra(ActivityConstants.YEAR, viewModel.getYearName());
                    startActivity(nextIntent);
                    overridePendingTransition(R.anim.enter_right, R.anim.exit_left);
                });
            }else{
                TextView alert = findViewById(R.id.eventname_alert_text_view);
                alert.setText("年度名を入力してください");
            }
        });

        final EditText yearNameEditText = findViewById(R.id.new_year_name_edit_text);


        yearNameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String inputStr= s.toString();
                viewModel.setYearName(inputStr);
                Log.i(NewYearViewModel.class.getName(), s.toString());
                if(inputStr.length() > 0){
                    finishNaming = true;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });


    }
    public void newYearCreated(View view){
        Intent intent = new Intent(this, MessagesActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right, R.anim.exit_left);
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
                intent.putExtra(EVENT_ID, eventId);
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