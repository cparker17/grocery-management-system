package com.example.groceryorderapp.service;

import com.example.groceryorderapp.exceptions.NoSuchGroceryOrderException;
import com.example.groceryorderapp.model.GroceryOrder;
import com.example.groceryorderapp.repository.GroceryOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroceryOrderService {
    @Autowired
    GroceryOrderRepo groceryOrderRepo;

    public GroceryOrder addGroceryOrder(GroceryOrder groceryOrder) {
        return (GroceryOrder) groceryOrderRepo.save(groceryOrder);
    }

    public Optional getById(Long id) throws NoSuchGroceryOrderException {
        if (groceryOrderRepo.findById(id).isEmpty()) {
            throw new NoSuchGroceryOrderException("A grocery order with this id does not exist.");
        }
        return groceryOrderRepo.findById(id);
    }

/*
    public GroceryOrder addGroceryOrder(GroceryOrder groceryOrder) {
        return groceryOrderRepo.save(groceryOrder);
    }

    public GroceryOrder getById(Long id) throws NoSuchGroceryOrderException {
        if (groceryOrderRepo.findById(id).isEmpty()) {
            throw new NoSuchGroceryOrderException("A grocery order with this id does not exist.");
        }
        return groceryOrderRepo.findById(id);
    }

 */
}
