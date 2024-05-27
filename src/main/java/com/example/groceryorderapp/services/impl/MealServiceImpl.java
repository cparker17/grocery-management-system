package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.domain.RecipeInstruction;
import com.example.groceryorderapp.enums.Location;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.model.RecipeWrapper;
import com.example.groceryorderapp.repositories.IngredientRepo;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.repositories.RecipeInstructionRepo;
import com.example.groceryorderapp.services.MealService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    MealRepo mealRepo;

    @Autowired
    IngredientRepo ingredientRepo;

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    RecipeInstructionRepo recipeInstructionRepo;

    @Override
    @Transactional
    public Meal saveMeal(Meal meal, RecipeWrapper recipeWrapper) {
        persistRecipe(meal, recipeWrapper);
        persistIngredients(meal);
        return mealRepo.save(meal);
    }

    private void persistRecipe(Meal meal, RecipeWrapper recipeWrapper) {
        List<RecipeInstruction> recipe = convertRecipeWrapperToRecipeInstructionList(recipeWrapper);
        meal.setRecipe(recipe);
        recipeInstructionRepo.saveAll(recipe);
    }

    private List<RecipeInstruction> convertRecipeWrapperToRecipeInstructionList(RecipeWrapper recipeWrapper) {
        List<RecipeInstruction> recipeInstructionList = new ArrayList<>();
        String[] recipeInstructions = recipeWrapper.getRecipeInstructions().split("\\.");
        for (String instruction : recipeInstructions) {
            recipeInstructionList.add(new RecipeInstruction(instruction.trim()));
        }
        return recipeInstructionList;
    }

    private void persistIngredients(Meal meal) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient ingredient : meal.getIngredients()) {
            switch (ingredient.getLocationString()) {
                case "Freezer" ->
                        ingredients.add(new Ingredient(ingredient.getDescription().toLowerCase(), Location.FREEZER));
                case "Refrigerator" ->
                        ingredients.add(new Ingredient(ingredient.getDescription().toLowerCase(), Location.REFRIGERATOR));
                case "Cabinet" ->
                        ingredients.add(new Ingredient(ingredient.getDescription().toLowerCase(), Location.CABINET));
            }
        }
        meal.setIngredients(ingredients);
        ingredientRepo.saveAll(ingredients);
    }

    @Override
    public void updateMeal(Meal meal) {
        ingredientRepo.deleteAll(meal.getIngredients());
        ingredientRepo.saveAll(meal.getIngredients());
        recipeInstructionRepo.deleteAll(meal.getRecipe());
        recipeInstructionRepo.saveAll(meal.getRecipe());
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
        return mealRepo.findById(id)
                .orElseThrow(() -> new NoSuchMealException("A meal with that id does not exist."));
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
        List<Meal> mealSchedule = getCurrentMealSchedule();
        switch (LocalDate.now().getDayOfWeek()) {
            case SUNDAY -> {return mealSchedule.get(0);}
            case MONDAY -> {return mealSchedule.get(1);}
            case TUESDAY -> {return mealSchedule.get(2);}
            case WEDNESDAY -> {return mealSchedule.get(3);}
            case THURSDAY -> {return mealSchedule.get(4);}
            case FRIDAY -> {return mealSchedule.get(5);}
            case SATURDAY -> {return mealSchedule.get(6);}
        }
        return null;
    }

    private List<Meal> getCurrentMealSchedule() throws NoMealPlanException {
        return mealPlanRepo.findById(1L)
                .orElseThrow(() -> new NoMealPlanException("No meal plan completed")).getMeals();
    }
}
