package com.example.groceryorderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryOrderRepo<GroceryOrder> extends JpaRepository<GroceryOrder, Long> {
}
