package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.MealSchedule;
import com.example.groceryorderapp.model.Day;
import org.springframework.stereotype.Service;

@Service
public interface MealScheduleService {
    public MealSchedule persistNewMealSchedule(Day mealSchedule);
}
