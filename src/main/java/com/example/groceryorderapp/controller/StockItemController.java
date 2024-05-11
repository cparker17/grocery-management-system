package com.example.groceryorderapp.controller;

import com.example.groceryorderapp.model.StockItem;
import com.example.groceryorderapp.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
public class StockItemController {

    @Autowired
    StockItemService foodItemService;

    @PostMapping("/new-food")
    public String addFoodItem(Model model, @ModelAttribute("foodItem") StockItem foodItem){
        model.addAttribute(foodItemService.addFoodItem(foodItem));
        return "view-stockItem";
    }



}
