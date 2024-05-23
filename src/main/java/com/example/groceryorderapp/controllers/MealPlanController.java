package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.model.MealPlanWrapper;
import com.example.groceryorderapp.services.MealPlanService;
import com.example.groceryorderapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Objects;

@Controller
@RequestMapping("/meal-plan")
public class MealPlanController {
    @Autowired
    MealPlanService mealPlanService;

    @Autowired
    MealService mealService;

    @RequestMapping("/view")
    public String viewMealPlan(Model model) throws NoSuchMealException, NoMealPlanException {
        MealPlan mealPlan = mealPlanService.getCurrentMealPlan();
        if (mealPlan.getMealList() == null) {
            return "redirect:/meal-plan/new";
        }
        model.addAttribute("mealPlan", mealPlanService.getCurrentMealPlan());
        return "view-meal-plan";
    }

    @RequestMapping("/new")
    public String newMealPlan(Model model) throws NoSuchMealException {
        model.addAttribute("mealPlanWrapper", new MealPlanWrapper());
        model.addAttribute("meals", mealService.getAllMeals());
        return "new-meal-plan";
    }

    @RequestMapping("/save")
    public String saveMealPlan(@ModelAttribute("mealPlanWrapper") MealPlanWrapper mealPlanWrapper) {
        mealPlanService.saveMealPlan(mealPlanWrapper);
        return "redirect:/meal-plan/view";
    }

}
