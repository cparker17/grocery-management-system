package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.MealSchedule;
import com.example.groceryorderapp.model.Day;
import com.example.groceryorderapp.services.MealScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealScheduleFactory {

    @Autowired
    MealScheduleService mealScheduleService;

    public MealSchedule createNewMealSchedule(Day day) {
        return mealScheduleService.persistNewMealSchedule(day);
    }
}

