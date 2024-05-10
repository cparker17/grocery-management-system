package com.example.groceryorderapp.repository;

import com.example.groceryorderapp.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
}
