package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
    public Meal findMealByName(String name);
}
