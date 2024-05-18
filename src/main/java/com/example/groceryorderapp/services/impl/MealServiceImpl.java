package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Meal getTonightsMeal() {
        return Meal.builder()
                .name("Chicken Caesar Salad")
                .recipe("Lettuce, chicken...")
                .ingredients((List<Ingredient>) Ingredient.builder().description("Cut lettuce, bake chicken").build())
                .build();
    }
}
