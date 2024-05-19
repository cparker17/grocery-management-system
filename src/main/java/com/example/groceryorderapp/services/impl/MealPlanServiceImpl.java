package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.services.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Override
    public MealPlan getCurrentMealPlan() throws NoMealPlanException {
        Optional<MealPlan> mealPlanOptional = mealPlanRepo.findById(1L);
        if (mealPlanOptional.isEmpty()) {
            throw new NoMealPlanException("Add a new meal plan:");
        }
        return mealPlanOptional.get();
    }

    @Override
    public void createMealPlan(MealPlan mealPlan) {
        MealPlan mealPlanToPersist = MealPlan.builder()
                .id(1L)
                .mealList(mealPlan.getMealList())
                .build();
        mealPlanRepo.save(mealPlanToPersist);
    }
}
