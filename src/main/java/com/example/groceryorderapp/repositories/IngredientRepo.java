package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
}
