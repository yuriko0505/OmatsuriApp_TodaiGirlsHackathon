package com.example.natsuyasai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.natsuyasai.R;
import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.viewmodels.NewEventViewModel;

import java.util.Objects;

public class NewEventActivity extends AppCompatActivity {

    private Boolean finishNaming = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_newevent);

        //アクションバーのオブジェクトを取得
        ActionBar actionBar = getSupportActionBar();
        //アクションバーに「戻るボタン」を追加
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(true);

        final NewEventViewModel viewModel = new ViewModelProvider(
                this,
                ViewModelProvider.Factory.from(NewEventViewModel.initializer)
        ).get(NewEventViewModel.class);

        Button createEventButton = findViewById(R.id.button_create_qr);
        // lambda式
        createEventButton.setOnClickListener(v -> {
            if(finishNaming){
                final Event event = viewModel.saveEvent();
                final Intent intent = new Intent(getApplication(), EventCreatedActivity.class);
                intent.putExtra(ActivityConstants.EVENT_ID, event.eventId);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_right, R.anim.exit_left);
            }else{
                TextView alert = findViewById(R.id.eventname_alert_text_view);
                alert.setText("イベント名を入力してください");
            }
        });

        final EditText eventEditText = findViewById(R.id.new_event_name_edit_text);


        eventEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String inputStr= s.toString();
                viewModel.setEventName(inputStr);
                Log.i(NewEventViewModel.class.getName(), s.toString());
                if(inputStr.length() > 0){
                    finishNaming = true;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

        });
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
                Intent intent = new Intent(getApplication(), HomeActivity.class);
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
