package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.StockItem;
import org.springframework.stereotype.Service;

@Service
public interface StockItemService {
    StockItem addFoodItem(StockItem stockItem);
}
