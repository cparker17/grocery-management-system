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

    public Meal saveMeal(Meal meal) {
        Meal mealToPersist = new Meal();
        mealToPersist.builder()
                .name(meal.getName())
                .ingredients(meal.getIngredients())
                .recipe(meal.getRecipe())
                .build();
        return mealRepo.save(mealToPersist);
    }

    public void updateMeal(Meal meal) {
        mealRepo.save(meal);
    }

    public void deleteMeal(Long id) throws NoSuchMealException {
        if (mealRepo.findById(id).isEmpty()) {
            throw new NoSuchMealException("A meal with that id does not exist");
        }
        mealRepo.deleteById(id);
    }

    public Meal viewMeal(Long id) throws NoSuchMealException{
        if (mealRepo.findById(id).isEmpty()) {
            throw new NoSuchMealException("A meal with that id does not exist");
        }
        return mealRepo.findById(id).get();
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepo.findAll();
    }
}
