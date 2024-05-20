package com.example.groceryorderapp.domain;

import com.example.groceryorderapp.model.Day;
import com.example.groceryorderapp.services.MealScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MealScheduleFactory {

    @Autowired
    MealScheduleService mealScheduleService;

    public MealSchedule createNewMealSchedule(Day day) {
        MealSchedule mealSchedule = new MealSchedule(day.name(), new Meal());
        return mealScheduleService.persistNewMealSchedule(mealSchedule);
    }
}

