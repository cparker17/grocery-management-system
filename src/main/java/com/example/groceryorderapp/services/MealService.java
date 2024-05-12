package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.Meal;
import org.springframework.stereotype.Service;

@Service
public interface MealService {
    void saveMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(Long id);

    Object viewMeal(Long id);
}
