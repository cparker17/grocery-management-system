package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.MealSchedule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealScheduleService {
    List<MealSchedule> createMealSchedulesForWeek();
}
