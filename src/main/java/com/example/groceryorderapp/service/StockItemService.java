package com.example.groceryorderapp.service;

import com.example.groceryorderapp.model.StockItem;
import com.example.groceryorderapp.exceptions.NoSuchStockItemException;
import com.example.groceryorderapp.repository.StockItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockItemService {

    @Autowired
    StockItemRepo stockItemRepo;

    public StockItem getFoodItemById(Long foodItemId) throws NoSuchStockItemException {
        Optional<StockItem> optionalFoodItem = stockItemRepo.findById(foodItemId);
        if(optionalFoodItem.isPresent()) {
            return optionalFoodItem.get();
        } else {
            throw new NoSuchStockItemException("A food item with that id does not exist.");
        }

    }

    public StockItem addFoodItem(StockItem foodItem) {
        return stockItemRepo.save(foodItem);
    }
}
