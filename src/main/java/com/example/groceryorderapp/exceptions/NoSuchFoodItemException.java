package com.example.groceryorderapp.exceptions;

public class NoSuchFoodItemException extends Exception{
    public NoSuchFoodItemException(String message) {
        super(message);
    }
}
