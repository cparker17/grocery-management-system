package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanRepo extends JpaRepository<MealPlan, Long> {
}
