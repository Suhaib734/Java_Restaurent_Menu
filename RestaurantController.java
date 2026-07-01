package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private MenuItemRepository repository;

    // Inject our new OOP Calculator
    @Autowired
    private StandardBillCalculator billCalculator;

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return repository.findAll();
    }

    @PostMapping("/checkout")
    public Map<String, Object> checkout(@RequestBody OrderRequest order) {
        double subtotal = 0;
        List<String> receiptDetails = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : order.getItems().entrySet()) {
            MenuItem item = repository.findById(entry.getKey()).orElse(null);
            if (item != null && entry.getValue() > 0) {
                double itemTotal = item.getPrice() * entry.getValue();
                subtotal += itemTotal;
                receiptDetails.add(item.getName() + " (x" + entry.getValue() + ") : ₹" + itemTotal);
            }
        }

        // USING OUR OOP CONCEPTS HERE:
        // We call the methods inherited and overridden in our subclass instead of doing manual math
        double gst = billCalculator.calculateGst(subtotal); 
        double totalBill = billCalculator.calculateTotalBill(subtotal);

        Map<String, Object> response = new HashMap<>();
        response.put("details", receiptDetails);
        response.put("subtotal", subtotal);
        response.put("gst", gst);
        response.put("total", totalBill);

        return response;
    }
}