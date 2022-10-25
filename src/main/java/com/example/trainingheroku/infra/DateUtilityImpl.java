package com.example.trainingheroku.infra;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateUtilityImpl implements DateUtility {
    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
