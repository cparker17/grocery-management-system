package com.example.groceryorderapp.services;

import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.exceptions.NoSuchStockItemException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface StockItemService {
    StockItem getStockItem(Long id) throws NoSuchStockItemException;

    StockItem addStockItem(StockItem stockItem);

    List<StockItem> getAllStockItems();

    void deleteStockItem(Long id) throws NoSuchStockItemException;

    void updateStockItem(StockItem stockItem) throws NoSuchStockItemException;
}
