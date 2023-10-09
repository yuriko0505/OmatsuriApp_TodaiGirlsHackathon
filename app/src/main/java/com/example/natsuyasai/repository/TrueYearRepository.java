package com.example.natsuyasai.repository;

import com.example.natsuyasai.domain.Year;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrueYearRepository implements YearRepository{
    @Override
    public void save(String eventId, Year year) {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://natsuyasai-e77f6-default-rtdb.asia-southeast1.firebasedatabase.app/");
        // URLの値は良さそう。
        DatabaseReference yearsRef = database.getReference("events/"+eventId+"/years/"+year);
        yearsRef.setValue(year);
    }
}
