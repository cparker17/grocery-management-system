package com.example.groceryorderapp.service;

import com.example.groceryorderapp.model.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.repository.MealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {
    @Autowired
    MealRepo mealRepo;

    public Meal saveMeal(Meal meal) {
        Meal mealToPersist = new Meal();
        mealToPersist.builder()
                .name(meal.getName())
                .ingredients(meal.getIngredients())
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
}
