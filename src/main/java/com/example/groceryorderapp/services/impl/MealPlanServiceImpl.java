package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.MealScheduleRepo;
import com.example.groceryorderapp.services.MealPlanService;
import com.example.groceryorderapp.services.MealScheduleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    MealScheduleService mealScheduleService;

    @Override
    public MealPlan getCurrentMealPlan() throws NoMealPlanException {
        Optional<MealPlan> mealPlanOptional = mealPlanRepo.findById(1L);
        if (mealPlanOptional.isEmpty()) {

            return new MealPlan();
            //throw new NoMealPlanException("Add a new meal plan:");
        }
        return mealPlanOptional.get();
    }

    @Override
    @Transactional
    public MealPlan newMealPlan(MealPlan mealPlan) {
        MealPlan mealPlanToPersist = MealPlan.builder()
                .id(1L)
                .mealList(mealScheduleService.createMealSchedulesForWeek())
                .build();
        return mealPlanRepo.save(mealPlanToPersist);
    }

    @Override
    public void saveMealPlan(MealPlan mealPlan) {
        mealPlanRepo.save(mealPlan);
    }
}
