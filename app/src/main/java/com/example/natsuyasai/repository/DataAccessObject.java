package com.example.natsuyasai.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
/*
import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.ui.FirstFragment;
import com.example.natsuyasai.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
*/
public class DataAccessObject {
/*
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://natsuyasai-e77f6-default-rtdb.asia-southeast1.firebasedatabase.app/");

    // 新しいeventを作成する。/event-id/new
    public void createNewEvent(String event_id, String event_name){
        // event は最初の階層なのでパス指定はしなくてよし？
        DatabaseReference ref = database.getReference();//home directoryなのでパスなしでOK？
        DatabaseReference eventsRef = ref.child("events");
        eventsRef.child(event_id).setValueAsync(new Event);

    }
    // あるeventについてすべての年度を取得する。/event-id/years
    public void getYears(String event_id){
        DatabaseReference myRef = database.getReference("events/"+event_id);
    }
    // あるeventについて新しい年度を登録する。 /event-id/year/new
    // あるeventのある年度について、新しいメッセージを作成する。 /event-id/year:year-int/message/new
    // あるeventのある年度について、すべてのメッセージを取得する。 /event-id/year:year-int/messages

    DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
                Log.d("STATE", "hello");
                NavHostFragment.findNavController(FirstFragment .this)
            .navigate(R.id.action_FirstFragment_to_SecondFragment);

                myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            String value = dataSnapshot.getValue(String.class);
            Log.d(TAG, "Value is: " + value);
//                        binding.textviewFirst.setText(value);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            // Failed to read value
            Log.w(TAG, "Failed to read value.", error.toException());
        }
    });

 */
}
