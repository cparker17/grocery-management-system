package com.example.groceryorderapp.service;

import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.exceptions.NoSuchStockItemException;
import com.example.groceryorderapp.repository.StockItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockItemService {

    @Autowired
    StockItemRepo stockItemRepo;

    public String addFoodItem(StockItem foodItem) {

    }

    public StockItem getFoodItemById(Long foodItemId) throws NoSuchStockItemException {
        Optional<StockItem> optionalFoodItem = stockItemRepo.findById(foodItemId);
        if(optionalFoodItem.isPresent()) {
            return optionalFoodItem.get();
        } else {
            throw new NoSuchStockItemException("A food item with that id does not exist.");
        }

    }

    public List<StockItem> getAllFoodItems() {

    }

    public StockItem updateFoodItem(Long id) {

    }
    public void deleteFoodItem(Long id) {

    }

}
