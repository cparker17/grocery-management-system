package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.exceptions.NoSuchStockItemException;
import com.example.groceryorderapp.services.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock-item")
public class StockItemController {

    @Autowired
    StockItemService stockItemService;

    @RequestMapping("/new")
    public String addStockItem(Model model, @ModelAttribute("stockItem") StockItem stockItem){
        model.addAttribute(stockItemService.addStockItem(stockItem));
        return "view-stock-item";
    }

    @RequestMapping("/view-all")
    public String viewAllStockItems(Model model) {
        model.addAttribute("stockItems", stockItemService.getAllStockItems());
        return "view-all-stock-items";
    }

    @RequestMapping("/delete")
    public String deleteStockItem(@ModelAttribute("stockItem") StockItem stockItem) throws NoSuchStockItemException {
        stockItemService.deleteStockItem(stockItem.getId());
        return "redirect:/view-all";
    }

    @RequestMapping("/update")
    public String updateStockItem(@ModelAttribute("stockItem") StockItem stockItem) throws NoSuchStockItemException {
        stockItemService.updateStockItem(stockItem);
        return "redirect:/view-all";
    }
}
