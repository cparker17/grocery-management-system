package com.example.groceryorderapp.repository;

import com.example.groceryorderapp.model.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepo extends JpaRepository<StockItem, Long> {
}
