package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.*;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchGroceryOrderException;
import com.example.groceryorderapp.model.GroceryOrderWrapper;
import com.example.groceryorderapp.model.ItemsToOrder;
import com.example.groceryorderapp.enums.Location;
import com.example.groceryorderapp.repositories.GroceryOrderRepo;
import com.example.groceryorderapp.repositories.MealPlanRepo;
import com.example.groceryorderapp.repositories.StockItemRepo;
import com.example.groceryorderapp.repositories.StoreItemRepo;
import com.example.groceryorderapp.services.GroceryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroceryOrderServiceImpl implements GroceryOrderService {
    @Autowired
    GroceryOrderRepo groceryOrderRepo;

    @Autowired
    StockItemRepo stockItemRepo;

    @Autowired
    MealPlanRepo mealPlanRepo;

    @Autowired
    StoreItemRepo storeItemRepo;

    @Override
    public GroceryOrder getGroceryOrder() throws NoSuchGroceryOrderException {
        return groceryOrderRepo.findById(1L)
                .orElseThrow(() ->  new NoSuchGroceryOrderException("A grocery order has not been created yet."));
    }

    @Override
    public ItemsToOrder getCabinetItemsToOrder() throws NoMealPlanException {
        ItemsToOrder cabinetItemsToOrder = new ItemsToOrder();
        MealPlan mealPlan = mealPlanRepo.findById(1L)
                .orElseThrow(() -> new NoMealPlanException("No meal plan created."));

        for (Meal meal : mealPlan.getMeals()) {
            for (Ingredient ingredient : meal.getIngredients()) {
                if (ingredient.getLocation() == Location.CABINET) {
                    cabinetItemsToOrder.getIngredientsToOrder().add(ingredient);
                }
            }
        }

        for (StockItem stockItem : stockItemRepo.findAll()) {
            if (stockItem.getLocation() == Location.CABINET) {
                cabinetItemsToOrder.getStockItemsToOrder().add(stockItem);
            }
        }

        return cabinetItemsToOrder;

    }

    @Override
    public ItemsToOrder getRefrigeratorItemsToOrder() throws NoMealPlanException {
        ItemsToOrder refrigeratorItemsToOrder = new ItemsToOrder();
        MealPlan mealPlan = mealPlanRepo.findById(1L)
                .orElseThrow(() -> new NoMealPlanException("No meal plan created."));

        for (Meal meal : mealPlan.getMeals()) {
            for (Ingredient ingredient : meal.getIngredients()) {
                if (ingredient.getLocation() == Location.REFRIGERATOR) {
                    refrigeratorItemsToOrder.getIngredientsToOrder().add(ingredient);
                }
            }
        }

        for (StockItem stockItem : stockItemRepo.findAll()) {
            if (stockItem.getLocation() == Location.REFRIGERATOR) {
                refrigeratorItemsToOrder.getStockItemsToOrder().add(stockItem);
            }
        }

        return refrigeratorItemsToOrder;
    }

    @Override
    public ItemsToOrder getFreezerItemsToOrder() throws NoMealPlanException {
        ItemsToOrder freezerItemsToOrder = new ItemsToOrder();
        MealPlan mealPlan = mealPlanRepo.findById(1L)
                .orElseThrow(() -> new NoMealPlanException("No meal plan created."));

        for (Meal meal : mealPlan.getMeals()) {
            for (Ingredient ingredient : meal.getIngredients()) {
                if (ingredient.getLocation() == Location.FREEZER) {
                    freezerItemsToOrder.getIngredientsToOrder().add(ingredient);
                }
            }
        }

        for (StockItem stockItem : stockItemRepo.findAll()) {
            if (stockItem.getLocation() == Location.FREEZER) {
                freezerItemsToOrder.getStockItemsToOrder().add(stockItem);
            }
        }

        return freezerItemsToOrder;
    }

    @Override
    public GroceryOrder createGroceryOrder(GroceryOrderWrapper groceryOrderWrapper) {
        List<StoreItem> itemsToOrder = new ArrayList<>();
        for (String itemToOrder : groceryOrderWrapper.getItemsToOrder()) {
            itemsToOrder.add(new StoreItem(itemToOrder));
        }
        GroceryOrder groceryOrderToPersist = new GroceryOrder();
        groceryOrderToPersist.setId(1L);
        groceryOrderToPersist.setItemsToOrder(storeItemRepo.saveAll(itemsToOrder));
        return groceryOrderRepo.save(groceryOrderToPersist);
    }

    @Override
    public GroceryOrder updateGroceryOrder(GroceryOrder groceryOrder) {
        groceryOrderRepo.deleteAll();
        groceryOrder.setId(1L);
        return groceryOrderRepo.save(groceryOrder);
    }
}
