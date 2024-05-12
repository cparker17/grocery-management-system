package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.services.MealService;
import com.example.groceryorderapp.services.impl.MealServiceImpl;
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
        mealService.saveMeal(meal);
        model.addAttribute(meal);
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


}
