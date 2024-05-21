package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.MealSchedule;
import com.example.groceryorderapp.repositories.MealScheduleRepo;
import com.example.groceryorderapp.services.MealScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealScheduleServiceImpl implements MealScheduleService {

    @Autowired
    MealScheduleRepo mealScheduleRepo;

    @Override
    public MealSchedule persistNewMealSchedule(MealSchedule mealSchedule) {
        return mealScheduleRepo.save(mealSchedule);
    }
}
