package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.MealSchedule;
import com.example.groceryorderapp.model.Day;
import com.example.groceryorderapp.repositories.MealScheduleRepo;
import com.example.groceryorderapp.services.MealScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealScheduleServiceImpl implements MealScheduleService {

    @Autowired
    MealScheduleRepo mealScheduleRepo;

    @Override
    @Transactional
    public List<MealSchedule> createMealSchedulesForWeek() {
        mealScheduleRepo.deleteAll();

        List<MealSchedule> mealScheduleList = new ArrayList<>();
        for (Day day : Day.values()) {
            MealSchedule mealSchedule = new MealSchedule(day.toString());
            mealScheduleList.add(mealScheduleRepo.save(mealSchedule));
        }
        return mealScheduleList;
    }
}
