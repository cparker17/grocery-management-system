package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchGroceryOrderException;
import com.example.groceryorderapp.domain.GroceryOrder;
import com.example.groceryorderapp.model.GroceryOrderWrapper;
import com.example.groceryorderapp.services.GroceryOrderService;
import com.example.groceryorderapp.services.MealPlanService;
import com.example.groceryorderapp.services.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grocery-order")
public class GroceryOrderController {

    @Autowired
    GroceryOrderService groceryOrderService;

    @Autowired
    MealPlanService mealPlanService;

    @Autowired
    StockItemService stockItemService;

    @RequestMapping("/setup")
    public String setupNewGroceryOrder(Model model) throws NoMealPlanException {
        model.addAttribute("groceryItems", groceryOrderService.getGroceryList());
        model.addAttribute("groceryOrderWrapper", new GroceryOrderWrapper());
        return "new-grocery-order";
    }

    @RequestMapping("/create")
    public String createGroceryOrder(Model model,
                                     @ModelAttribute("groceryOrder") GroceryOrderWrapper groceryOrderWrapper) {
        model.addAttribute("groceryOrder", groceryOrderService.createGroceryOrder(groceryOrderWrapper));
        return "view-grocery-order";
    }

    @RequestMapping("/update")
    public String updateGroceryOrder(Model model, @ModelAttribute("groceryOrder") GroceryOrder groceryOrder) {
        model.addAttribute("groceryOrder", groceryOrderService.updateGroceryOrder(groceryOrder));
        return "view-grocery-order";
    }

    @RequestMapping("/delete")
    public String deleteGroceryOrder(Model model, @ModelAttribute("groceryOrder") GroceryOrder groceryOrder) {
        return "home";
    }

    @RequestMapping("/view")
    public String viewGroceryOrder(Model model) throws NoSuchGroceryOrderException {
        model.addAttribute(groceryOrderService.getGroceryOrder());
        return "view-grocery-order";
    }
}
