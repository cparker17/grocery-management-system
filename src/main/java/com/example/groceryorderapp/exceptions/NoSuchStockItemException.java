package com.example.groceryorderapp.exceptions;

public class NoSuchStockItemException extends Exception{
    public NoSuchStockItemException(String message) {
        super(message);
    }
}
