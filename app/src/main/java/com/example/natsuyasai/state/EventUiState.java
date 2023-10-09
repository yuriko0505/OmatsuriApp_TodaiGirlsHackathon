package com.example.natsuyasai.state;

import com.example.natsuyasai.domain.Year;

import java.util.List;

public class EventUiState {
    private final List<Year> years;

    public EventUiState(List<Year> years) {
        this.years = years;
    }

    public List<Year> getYears() {
        return years;
    }
}
