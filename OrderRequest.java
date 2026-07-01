package com.example.demo;
import java.util.Map;

public class OrderRequest {
    // Maps Menu Item ID to Quantity
    private Map<Long, Integer> items; 
    public Map<Long, Integer> getItems() { return items; }
    public void setItems(Map<Long, Integer> items) { this.items = items; }
}