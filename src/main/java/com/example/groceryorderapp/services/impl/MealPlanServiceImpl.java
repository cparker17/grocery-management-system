package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.model.MealPlanWrapper;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.services.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    MealRepo mealRepo;

    @Override
    public MealPlan getCurrentMealPlan() {
        Optional<MealPlan> mealPlanOptional = mealPlanRepo.findById(1L);
        return mealPlanOptional.orElseGet(MealPlan::new);
    }

    @Override
    public void saveMealPlan(MealPlanWrapper mealPlanWrapper) {
        mealPlanRepo.deleteAll();
        MealPlan mealPlan = new MealPlan();
        mealPlan.setId(1L);
        mealPlan.setMeals(new ArrayList<>());
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getSundayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getMondayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getTuesdayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getWednesdayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getThursdayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getFridayMeal()));
        mealPlan.getMeals().add(mealRepo.findMealByName(mealPlanWrapper.getSaturdayMeal()));
        mealPlanRepo.save(mealPlan);
    }
}
