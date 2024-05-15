package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    MealRepo mealRepo;

    @Override
    public Meal saveMeal(Meal meal) {
        Meal mealToPersist = new Meal();
        mealToPersist.builder()
                .name(meal.getName())
                .ingredients(meal.getIngredients())
                .recipe(meal.getRecipe())
                .build();
        return mealRepo.save(mealToPersist);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealRepo.save(meal);
    }

    @Override
    public void deleteMeal(Long id) throws NoSuchMealException {
        if (mealRepo.findById(id).isEmpty()) {
            throw new NoSuchMealException("A meal with that id does not exist");
        }
        mealRepo.deleteById(id);
    }

    @Override
    public Meal viewMeal(Long id) throws NoSuchMealException{
        if (mealRepo.findById(id).isEmpty()) {
            throw new NoSuchMealException("A meal with that id does not exist");
        }
        return mealRepo.findById(id).get();
    }

    @Override
    public List<Meal> getAllMeals() throws NoSuchMealException {
        List<Meal> meals = mealRepo.findAll();
        if (meals.isEmpty()) {
            throw new NoSuchMealException("There aren't any saved meals.");
        }
        return meals;
    }
}