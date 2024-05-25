package com.example.groceryorderapp.repositories;

import com.example.groceryorderapp.domain.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepo extends JpaRepository<StoreItem, Long> {
}
