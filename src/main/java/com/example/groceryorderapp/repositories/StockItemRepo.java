package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.MealSchedule;
import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.model.Day;
import com.example.groceryorderapp.services.MealScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface StockItemRepo extends JpaRepository<StockItem, Long> {
}
