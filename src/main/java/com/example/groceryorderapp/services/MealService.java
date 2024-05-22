package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MealService {
    Meal saveMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(Long id) throws NoSuchMealException;

    Meal getMeal(Long id) throws NoSuchMealException;

    List<Meal> getAllMeals() throws NoSuchMealException;

    Meal getTonightsMeal();
}
