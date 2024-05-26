package com.example.groceryorderapp.services.impl;

import com.example.groceryorderapp.domain.StockItem;
import com.example.groceryorderapp.enums.Location;
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
        return stockItemRepo.findById(id)
                .orElseThrow(() -> new NoSuchStockItemException("A stock item with that id does not exist"));

    }

    @Override
    public StockItem addStockItem(StockItem foodItem) {
        switch (foodItem.getLocationString()) {
            case "Freezer":
                foodItem.setLocation(Location.FREEZER);
                break;
            case "Refrigerator":
                foodItem.setLocation(Location.REFRIGERATOR);
                break;
            case "Cabinet":
                foodItem.setLocation(Location.CABINET);
                break;
        }
        return stockItemRepo.save(foodItem);
    }

    @Override
    public List<StockItem> getAllStockItems() {
        List<StockItem> stockItems = stockItemRepo.findAll();
        for (StockItem stockItem : stockItems) {
            switch (stockItem.getLocation()) {
                case FREEZER -> stockItem.setLocationString("Freezer");
                case REFRIGERATOR -> stockItem.setLocationString("Refrigerator");
                case CABINET -> stockItem.setLocationString("Cabinet");
            }
        }
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
        switch (stockItem.getLocationString()) {
            case "Freezer" -> stockItem.setLocation(Location.FREEZER);
            case "Refrigerator" -> stockItem.setLocation(Location.REFRIGERATOR);
            case "Cabinet" -> stockItem.setLocation(Location.CABINET);
        }

        stockItemRepo.save(stockItem);
    }
}
