package com.example.groceryorderapp.service;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.repository.MealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {
    @Autowired
    MealRepo mealRepo;

    public Meal saveMeal(Meal meal) {
        meal.setName(meal.getName());
        return mealRepo.save(meal);
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
