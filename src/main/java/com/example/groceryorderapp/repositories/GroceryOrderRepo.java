package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryOrderRepo extends JpaRepository<GroceryOrder, Long> {
}
