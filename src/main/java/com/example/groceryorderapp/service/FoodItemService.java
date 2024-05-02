package com.example.groceryorderapp.service;

import com.example.groceryorderapp.domain.FoodItem;
import com.example.groceryorderapp.exceptions.NoSuchFoodItemException;
import com.example.groceryorderapp.repository.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    FoodItemRepo foodItemRepo;

    public String addFoodItem(FoodItem foodItem) {

    }

    public FoodItem getFoodItemById(Long foodItemId) throws NoSuchFoodItemException {
        Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(foodItemId);
        if(optionalFoodItem.isPresent()) {
            return optionalFoodItem.get();
        } else {
            throw new NoSuchFoodItemException("A food item with that id does not exist.");
        }

    }

    public List<FoodItem> getAllFoodItems() {

    }

    public FoodItem updateFoodItem(Long id) {

    }
    public void deleteFoodItem(Long id) {

    }

}
