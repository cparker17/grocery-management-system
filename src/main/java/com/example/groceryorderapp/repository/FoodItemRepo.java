package com.example.groceryorderapp.repository;

import com.example.groceryorderapp.domain.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Long> {
}
