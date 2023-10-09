package com.example.natsuyasai.repository;

import com.example.natsuyasai.domain.Event;
import com.example.natsuyasai.domain.Year;

public interface YearRepository {
    // Define methods here
    void save(String eventId, Year year);
}
