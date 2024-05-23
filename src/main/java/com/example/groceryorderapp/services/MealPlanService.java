package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.model.MealPlanWrapper;
import org.springframework.stereotype.Service;

@Service
public interface MealPlanService {
    MealPlan getCurrentMealPlan() throws NoMealPlanException;
    void saveMealPlan(MealPlanWrapper mealPlan);
}
