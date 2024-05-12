package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.GroceryOrder;
import org.springframework.stereotype.Service;

@Service
public interface GroceryOrderService {
    GroceryOrder addGroceryOrder(GroceryOrder groceryOrder);
    GroceryOrder getById(Long id);
}
