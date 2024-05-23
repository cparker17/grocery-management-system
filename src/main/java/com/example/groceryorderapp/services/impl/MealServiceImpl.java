package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.repositories.IngredientRepo;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.services.MealPlanService;
import com.example.groceryorderapp.services.MealService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    MealRepo mealRepo;

    @Autowired
    IngredientRepo ingredientRepo;

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Override
    @Transactional
    public Meal saveMeal(Meal meal) {
        for (Ingredient ingredient : meal.getIngredients()) {
            ingredientRepo.save(ingredient);
        }
        return mealRepo.save(meal);
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
    public Meal getMeal(Long id) throws NoSuchMealException {
        Optional<Meal> mealOptional = mealRepo.findById(id);
        if (mealOptional.isEmpty()) {
            throw new NoSuchMealException("A meal with that id does not exist");
        }
        return mealOptional.get();
    }

    @Override
    public List<Meal> getAllMeals() throws NoSuchMealException {
        List<Meal> meals = mealRepo.findAll();
        if (meals.isEmpty()) {
            throw new NoSuchMealException("There aren't any saved meals.");
        }
        return meals;
    }

    @Override
    public Meal getTonightsMeal() throws NoMealPlanException {
        Optional<MealPlan> mealPlanOptional = mealPlanRepo.findById(1L);

        if (mealPlanOptional.isEmpty()) {
            throw new NoMealPlanException("No meal plan completed.");
        } else {
            MealPlan mealPlan = mealPlanOptional.get();
            List<Meal> mealSchedule = mealPlan.getMeals();

            switch (LocalDate.now().getDayOfWeek()) {
                case SUNDAY -> {
                    return mealSchedule.get(0);
                }
                case MONDAY -> {
                    return mealSchedule.get(1);
                }
                case TUESDAY -> {
                    return mealSchedule.get(2);
                }
                case WEDNESDAY -> {
                    return mealSchedule.get(3);
                }
                case THURSDAY -> {
                    return mealSchedule.get(4);
                }
                case FRIDAY -> {
                    return mealSchedule.get(5);
                }
                case SATURDAY -> {
                    return mealSchedule.get(6);
                }
            }
        }
        return null;
    }
}
