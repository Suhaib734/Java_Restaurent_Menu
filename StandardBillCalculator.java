package com.example.demo;
import org.springframework.stereotype.Service;

@Service // Tells Spring Boot to manage this class
// INHERITANCE: The "extends" keyword means this class inherits from BillCalculator
public class StandardBillCalculator extends BillCalculator {

    // POLYMORPHISM: We take the abstract method from the parent and OVERRIDE it
    // to give it a specific form (in this case, a 5% GST calculation).
    @Override
    public double calculateGst(double subtotal) {
        return subtotal * 0.05; 
    }
}