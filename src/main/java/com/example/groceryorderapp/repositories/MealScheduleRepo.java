package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.MealSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealScheduleRepo extends JpaRepository<MealSchedule, Long> {
}
