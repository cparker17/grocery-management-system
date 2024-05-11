package com.example.groceryorderapp.repository;

import com.example.groceryorderapp.model.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryOrderRepo extends JpaRepository<GroceryOrder, Long> {
}
