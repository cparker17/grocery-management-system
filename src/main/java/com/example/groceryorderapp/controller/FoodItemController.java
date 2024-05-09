package com.example.groceryorderapp.controller;

import com.example.groceryorderapp.domain.FoodItem;
import com.example.groceryorderapp.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/new-food")
    public String addFoodItem(@ModelAttribute("food")FoodItem foodItem){
        return foodItemService.addFoodItem(foodItem);
    }

}
