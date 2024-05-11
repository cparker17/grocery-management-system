package com.example.groceryorderapp.controller;

import com.example.groceryorderapp.exceptions.NoSuchGroceryOrderException;
import com.example.groceryorderapp.model.GroceryOrder;
import com.example.groceryorderapp.service.GroceryOrderService;
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

    @RequestMapping("/save")
    public String createGroceryOrder(Model model, @ModelAttribute("groceryOrder")GroceryOrder groceryOrder) {
        model.addAttribute(groceryOrderService.addGroceryOrder(groceryOrder));
        return "view-grocery-order";
    }

    @RequestMapping("/update")
    public String updateGroceryOrder(Model model, @ModelAttribute("groceryOrder") GroceryOrder groceryOrder) {
        return "dashboard";
    }

    @RequestMapping("/delete")
    public String deleteGroceryOrder(Model model, @ModelAttribute("groceryOrder") GroceryOrder groceryOrder) {
        return "dashboard";
    }

    @RequestMapping("/view")
    public String viewGroceryOrder(Model model, @ModelAttribute("groceryOrder") GroceryOrder groceryOrder)
            throws NoSuchGroceryOrderException {
        model.addAttribute(groceryOrderService.getById(groceryOrder.getId()));
        return "view-grocery-order";
    }


}
