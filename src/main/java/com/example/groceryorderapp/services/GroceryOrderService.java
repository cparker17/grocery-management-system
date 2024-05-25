package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.GroceryOrder;
import com.example.groceryorderapp.exceptions.NoMealPlanException;
import com.example.groceryorderapp.exceptions.NoSuchGroceryOrderException;
import com.example.groceryorderapp.model.GroceryOrderWrapper;
import com.example.groceryorderapp.model.ItemsToOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroceryOrderService { 
    GroceryOrder getGroceryOrder() throws NoSuchGroceryOrderException;

    ItemsToOrder getCabinetItemsToOrder() throws NoMealPlanException;

    ItemsToOrder getRefrigeratorItemsToOrder() throws NoMealPlanException;

    ItemsToOrder getFreezerItemsToOrder() throws NoMealPlanException;

    GroceryOrder createGroceryOrder(GroceryOrderWrapper groceryOrderWrapper);

    GroceryOrder updateGroceryOrder(GroceryOrder groceryOrder);
}
