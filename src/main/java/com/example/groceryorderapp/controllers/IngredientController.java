package com.example.groceryorderapp.controllers;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.exceptions.NoSuchIngredientException;
import com.example.groceryorderapp.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @RequestMapping("/view")
    public String viewIngredient(Model model, @ModelAttribute("ingredient") Ingredient ingredient) throws NoSuchIngredientException {
        model.addAttribute("ingredient", ingredientService.getIngredient(ingredient.getId()));
        return "view-ingredient";
    }

    @RequestMapping("/edit/{id}")
    public String editIngredient(Model model, @PathVariable("id") Long id) throws NoSuchIngredientException {
        model.addAttribute("ingredient", ingredientService.getIngredient(id));
        return "update-ingredient";
    }

    @RequestMapping("/update")
    public String updateIngredient(@ModelAttribute("ingredient")Ingredient ingredient) {
        ingredientService.updateIngredient(ingredient);
        return "redirect:/ingredient/view";
    }

    @RequestMapping("/delete")
    public String deleteIngredient(@ModelAttribute("ingredient") Ingredient ingredient) {
        ingredientService.deleteIngredient(ingredient);
        return "redirect:/view/home";
    }

    @RequestMapping("/add")
    public String addNewIngredient(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "new-ingredient";
    }

    @RequestMapping("/create")
    public String createIngredient(Model model, @ModelAttribute("ingredient") Ingredient ingredient) {
        model.addAttribute("ingredient", ingredientService.saveIngredient(ingredient));
        return "view-ingredient";
    }
}
