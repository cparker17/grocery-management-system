package com.example.groceryorderapp.controller;

import com.example.groceryorderapp.model.StockItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/dashboard")
    public String viewDashboard() {
        return "dashboard";
    }

    @RequestMapping("/new-food")
    public String viewNewFoodPage(Model model) {
        model.addAttribute("foodItem", new StockItem());
        return "new-foodItem";
    }
}
