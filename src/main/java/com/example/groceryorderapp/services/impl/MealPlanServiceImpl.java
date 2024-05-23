package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.model.Day;
import com.example.groceryorderapp.model.MealPlanWrapper;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.MealRepo;
import com.example.groceryorderapp.services.MealPlanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    MealRepo mealRepo;

    @Override
    public MealPlan getCurrentMealPlan() throws NoMealPlanException {
        Optional<MealPlan> mealPlanOptional = mealPlanRepo.findById(1L);
        if (mealPlanOptional.isEmpty()) {

            return new MealPlan();
            //throw new NoMealPlanException("Add a new meal plan:");
        }
        return mealPlanOptional.get();
    }

    @Override
    @Transactional
    public MealPlan newMealPlan(MealPlan mealPlan) {
        List<Meal> meals = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            meals.add(new Meal());
        }

        MealPlan mealPlanToPersist = new MealPlan();
        mealPlanToPersist.setId(1L);
        mealPlanToPersist.setMealList(meals);
        mealPlanRepo.save(mealPlanToPersist);

        return mealPlanToPersist;
    }

    @Override
    public void saveMealPlan(MealPlanWrapper mealPlanWrapper) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setId(1L);
        for (Map.Entry<Day, Meal> entry : mealPlanWrapper.getMealSchedule().entrySet()) {
            mealPlan.getMealList().add(entry.getValue());
        }
        mealPlanRepo.save(mealPlan);
    }
}
