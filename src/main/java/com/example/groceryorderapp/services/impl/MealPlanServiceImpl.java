package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.model.MealPlanWrapper;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.services.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    MealRepo mealRepo;

    @Override
    public MealPlan getCurrentMealPlan() {
        return mealPlanRepo.findById(1L)
                .orElseGet(MealPlan::new);
    }

    @Override
    public void saveMealPlan(MealPlanWrapper mealPlanWrapper) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setId(1L);
        setMealsFromMealPlanWrapper(mealPlan, mealPlanWrapper);
        mealPlanRepo.save(mealPlan);
    }

    public List<Meal> setMealsFromMealPlanWrapper(MealPlan mealPlan, MealPlanWrapper mealPlanWrapper) {
        List<Meal> mealsList = new ArrayList<>();
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getSundayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getMondayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getTuesdayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getWednesdayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getThursdayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getFridayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getSaturdayMeal()));
        return mealsList;
    }
}
