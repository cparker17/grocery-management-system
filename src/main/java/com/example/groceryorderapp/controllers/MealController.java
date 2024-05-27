package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.exceptions.NoSuchMealException;
import com.example.groceryorderapp.model.RecipeWrapper;
import com.example.groceryorderapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meals")
public class MealController {
    @Autowired
    MealService mealService;

    @RequestMapping("/create")
    public String createNewMeal(Model model, @ModelAttribute("meal") Meal meal,
                                @ModelAttribute("RecipeWrapper") RecipeWrapper recipeWrapper) {
        model.addAttribute("meal", mealService.saveMeal(meal, recipeWrapper));
        return "redirect:/meals/view-all";
    }

    @RequestMapping("/edit/{id}")
    public String editMeal(Model model, @PathVariable(name = "id") long id) {
        try {
            model.addAttribute("meal", mealService.getMeal(id));
            return "update-meal";
        } catch (NoSuchMealException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }

    }

    @RequestMapping("/update")
    public String updateMeal(@ModelAttribute("meal") Meal meal) {
        mealService.updateMeal(meal);
        return "redirect:/meals/view-all";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMeal(Model model, @PathVariable(name = "id") Long id) {
        try {
            mealService.deleteMeal(id);
            return "redirect:/meals/view-all";
        } catch (NoSuchMealException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }

    }

    @RequestMapping("/view/{id}")
    public String viewMeal(Model model, @PathVariable(name = "id") Long id) throws NoSuchMealException {
        try {
            model.addAttribute("meal", mealService.getMeal(id));
            return "view-meal";
        } catch (NoSuchMealException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }

    }

    @RequestMapping("/view-all")
    public String viewAllMeals(Model model) throws NoSuchMealException {
        try {
            model.addAttribute("meals", mealService.getAllMeals());
            return "view-all-meals";
        } catch (NoSuchMealException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }

    }

    @RequestMapping("/add")
    public String addNewMeal(Model model, @ModelAttribute("meal") Meal meal) {
        model.addAttribute("meal", new Meal(meal.getNumberOfIngredients()));
        model.addAttribute("recipeInstructionWrapper", new RecipeWrapper());
        return "new-meal";
    }
}
