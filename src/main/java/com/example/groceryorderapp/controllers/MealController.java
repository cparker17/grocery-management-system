package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meals")
public class MealController {
    @Autowired
    MealService mealService;

    @RequestMapping("/save")
    public String saveMeal(Model model, @ModelAttribute("meal") Meal meal) throws NoSuchMealException {
        System.out.println(meal.toString());
        model.addAttribute("meal", mealService.saveMeal(meal));
        return "view-meal";
    }

    @RequestMapping("/update")
    public String updateMeal(@ModelAttribute("meal") Meal meal) throws NoSuchMealException {
        mealService.updateMeal(meal);
        return "redirect:/dashboard";
    }

    @RequestMapping("/delete")
    public String deleteMeal(@ModelAttribute("meal") Meal meal) throws NoSuchMealException {
        mealService.deleteMeal(meal.getId());
        return "redirect:/dashboard";
    }

    @RequestMapping("/view")
    public String viewMeal(Model model, @ModelAttribute("meal") Meal meal) throws NoSuchMealException {
        model.addAttribute("meal", mealService.viewMeal(meal.getId()));
        return "view-meal";
    }

    @RequestMapping("/view-all")
    public String viewAllMeals(Model model) throws NoSuchMealException {
        model.addAttribute("meals", mealService.getAllMeals());
        return "view-all-meals";
    }

    @RequestMapping("/add")
    public String addNewMeal(Model model, @ModelAttribute("meal") Meal meal) {
        model.addAttribute("meal", new Meal(meal.getNumberOfIngredients()));
        return "new-meal";
    }
}
