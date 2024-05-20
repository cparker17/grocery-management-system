package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.MealSchedule;
import org.springframework.stereotype.Service;

@Service
public interface MealScheduleService {
    public MealSchedule persistNewMealSchedule(MealSchedule mealSchedule);
}
