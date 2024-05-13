package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.StockItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping("/home")
    public String viewHomePage() {
        return "home";
    }

    @RequestMapping("/new-stock-item")
    public String viewNewFoodPage(Model model) {
        model.addAttribute("foodItem", new StockItem());
        return "new-stock-item";
    }

    @RequestMapping("/update-stock-item")
    public String viewUpdateStockItemPage(Model model, @ModelAttribute("stockItem") StockItem stockItem) {
        model.addAttribute("stockItem", stockItem);
        return "update-stock-item";
    }


}
