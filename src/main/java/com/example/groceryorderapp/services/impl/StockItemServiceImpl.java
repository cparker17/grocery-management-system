package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.exceptions.NoSuchStockItemException;
import com.example.groceryorderapp.repositories.StockItemRepo;
import com.example.groceryorderapp.services.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StockItemServiceImpl implements StockItemService {

    @Autowired
    StockItemRepo stockItemRepo;

    @Override
    public StockItem getStockItem(Long id) throws NoSuchStockItemException {
        Optional<StockItem> optionalStockItem = stockItemRepo.findById(id);
        if(optionalStockItem.isPresent()) {
            return optionalStockItem.get();
        } else {
            throw new NoSuchStockItemException("A food item with that id does not exist.");
        }

    }

    @Override
    public StockItem addStockItem(StockItem foodItem) {
        return stockItemRepo.save(foodItem);
    }

    @Override
    public List<StockItem> getAllStockItems() {
        List<StockItem> stockItems = stockItemRepo.findAll();
        return stockItems;
    }

    @Override
    public void deleteStockItem(Long id) throws NoSuchStockItemException {
        if(stockItemRepo.findById(id).isEmpty()) {
            throw new NoSuchStockItemException("A stock item with this id does not exist.");
        }
        stockItemRepo.deleteById(id);
    }

    @Override
    public void updateStockItem(StockItem stockItem) throws NoSuchStockItemException {
        if (stockItemRepo.findById(stockItem.getId()).isEmpty()) {
            throw new NoSuchStockItemException("A stock item with this id does not exist.");
        }
        
        stockItemRepo.save(StockItem.builder()
                .id(stockItem.getId())
                .name(stockItem.getName())
                .location(stockItem.getLocation())
                .build());
    }
}
