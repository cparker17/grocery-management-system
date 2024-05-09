package com.example.groceryorderapp.service;

import com.example.groceryorderapp.domain.GroceryOrder;
import com.example.groceryorderapp.repository.GroceryOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroceryOrderService {
    @Autowired
    GroceryOrderRepo groceryOrderRepo;


    public GroceryOrder addGroceryOrder(GroceryOrder groceryOrder) {
        //return groceryOrderRepo.save(groceryOrder);
    }
}
