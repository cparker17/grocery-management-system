package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import org.springframework.stereotype.Service;

@Service
public interface MealPlanService {
    MealPlan getCurrentMealPlan() throws NoMealPlanException;
    MealPlan newMealPlan(MealPlan mealPlan);
    void saveMealPlan(MealPlan mealPlan);
}
