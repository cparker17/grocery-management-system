package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.exceptions.NoSuchIngredientException;
import com.example.groceryorderapp.repositories.IngredientRepo;
import com.example.groceryorderapp.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    IngredientRepo ingredientRepo;

    @Override
    public Ingredient getIngredient(Long id) throws NoSuchIngredientException {
        return ingredientRepo.findById(id)
                .orElseThrow(() -> new NoSuchIngredientException("An ingredient with this id does not exist"));
    }

    @Override
    public void updateIngredient(Ingredient ingredient) {
        ingredientRepo.save(ingredient);
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
        ingredientRepo.delete(ingredient);
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }
}
