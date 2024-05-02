package com.example.groceryorderapp.service;

import com.example.groceryorderapp.model.GroceryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroceryOrderService {

    public GroceryOrder getGroceryOrder() {
        GroceryOrder groceryOrder = new GroceryOrder();

        return groceryOrder;
    }

    public GroceryOrder createGroceryOrder(GroceryOrder groceryOrder) {
        
    }
}
