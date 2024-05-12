package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import org.springframework.stereotype.Service;

@Service
public interface MealService {
    Object saveMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(Long id) throws NoSuchMealException;

    Object viewMeal(Long id) throws NoSuchMealException;
}
