package com.example.groceryorderapp.repository;

import com.example.groceryorderapp.model.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepo extends JpaRepository<MealPlan, Long> {
}
