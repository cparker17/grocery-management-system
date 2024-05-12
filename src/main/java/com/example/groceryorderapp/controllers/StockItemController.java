package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.services.StockItemService;
import com.example.groceryorderapp.services.impl.StockItemServiceImpl;
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
    StockItemService stockItemService;

    @PostMapping("/new-food")
    public String addFoodItem(Model model, @ModelAttribute("stockItem") StockItem stockItem){
        model.addAttribute(stockItemService.addFoodItem(stockItem));
        return "view-stockItem";
    }



}
