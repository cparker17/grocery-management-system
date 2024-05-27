package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.exceptions.NoSuchStockItemException;
import com.example.groceryorderapp.services.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock-item")
public class StockItemController {

    @Autowired
    StockItemService stockItemService;

    @RequestMapping("/create")
    public String createNewStockItem(Model model) {
        model.addAttribute("stockItem", new StockItem());
        return "new-stock-item";
    }

    @RequestMapping("/new")
    public String addStockItem(Model model, @ModelAttribute("stockItem") StockItem stockItem) {
        stockItemService.addStockItem(stockItem);
        model.addAttribute("stockItems", stockItemService.getAllStockItems());
        return "view-all-stock-items";
    }

    @RequestMapping("/view-all")
    public String viewAllStockItems(Model model) {
        model.addAttribute("stockItems", stockItemService.getAllStockItems());
        return "view-all-stock-items";
    }

    @RequestMapping("/delete/{id}")
    public String deleteStockItem(Model model, @PathVariable(name = "id") Long id) {
        try {
            stockItemService.deleteStockItem(id);
            return "redirect:/stock-item/view-all";
        } catch (NoSuchStockItemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }
    }

    @RequestMapping("/edit/{id}")
    public String editStockItem(Model model, @PathVariable(name = "id") Long id) {
        try {
            model.addAttribute("stockItem", stockItemService.getStockItem(id));
            return "update-stock-item";
        } catch (NoSuchStockItemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }

    }

    @RequestMapping("/update")
    public String updateStockItem(Model model, @ModelAttribute("stockItem") StockItem stockItem) {
        try {
            stockItemService.updateStockItem(stockItem);
            return "redirect:/stock-item/view-all";
        } catch (NoSuchStockItemException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }

    }
}
