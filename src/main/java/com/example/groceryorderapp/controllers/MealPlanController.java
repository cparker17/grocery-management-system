package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
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
        model.addAttribute("meals", mealService.getAllMeals());
        model.addAttribute("mealPlan", mealPlanService.getCurrentMealPlan());

        return "new-meal-plan";
    }

    @RequestMapping("/new")
    public String newMealPlan(Model model, @ModelAttribute("mealPlan") MealPlan mealPlan) throws NoSuchMealException {
        model.addAttribute("mealPlan",
                mealPlanService.newMealPlan(Objects.requireNonNullElseGet(mealPlan, MealPlan::new)));
        model.addAttribute("meals", mealService.getAllMeals());
        return "new-meal-plan";
    }

    @RequestMapping("/save")
    public String saveMealPlan(@ModelAttribute("mealPlan") MealPlan mealPlan) {
        System.out.println("************************" + mealPlan.toString());
        mealPlanService.saveMealPlan(mealPlan);
        return "redirect:/meal-plan/view";
    }

}
