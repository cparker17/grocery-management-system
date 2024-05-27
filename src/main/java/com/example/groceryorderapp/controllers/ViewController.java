package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.Meal;
import com.example.groceryorderapp.domain.MealPlan;
import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchGroceryOrderException;
import com.example.groceryorderapp.services.GroceryOrderService;
import com.example.groceryorderapp.services.MealPlanService;
import com.example.groceryorderapp.services.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @Autowired
    MealService mealService;

    @Autowired
    MealPlanService mealPlanService;

    @Autowired
    GroceryOrderService groceryOrderService;

    @RequestMapping("/home")
    public String viewHomePage(Model model) throws NoMealPlanException, NoSuchGroceryOrderException {
        MealPlan mealPlan = mealPlanService.getCurrentMealPlan();
        if (mealPlan.getMeals().isEmpty()) {
            return "redirect:/meal-plan/new";
        }
        model.addAttribute("mealPlan", mealPlan);
        model.addAttribute("meal", mealService.getTonightsMeal());
        model.addAttribute("groceryOrder", groceryOrderService.getGroceryOrder());
        return "home";
    }

    @RequestMapping("help")
    public String viewHelpInformationPage() {
        return "help";
    }

    @RequestMapping("/new-stock-item")
    public String viewNewStockItemPage(Model model) {
        model.addAttribute("stockItem", new StockItem());
        return "new-stock-item";
    }

    @RequestMapping("/update-stock-item")
    public String viewUpdateStockItemPage(Model model, @ModelAttribute("stockItem") StockItem stockItem) {
        model.addAttribute("stockItem", stockItem);
        return "update-stock-item";
    }

    @RequestMapping("/new-meal")
    public String viewMealSetupPage(Model model) {
        model.addAttribute("meal", new Meal());
        return "meal-setup";
    }
}
