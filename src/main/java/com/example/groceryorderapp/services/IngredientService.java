package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.Ingredient;
import com.example.groceryorderapp.exceptions.NoSuchIngredientException;
import org.springframework.stereotype.Service;

@Service
public interface IngredientService {
    Ingredient getIngredient(Long id) throws NoSuchIngredientException;

    void updateIngredient(Ingredient ingredient);

    void deleteIngredient(Ingredient ingredient);
}
