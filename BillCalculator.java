package com.example.demo;

// ABSTRACTION: We create a blueprint for any type of bill calculator.
// We hide the complex details of HOW the math is done.
public abstract class BillCalculator {
    
    // Abstract method: It has no body. Subclasses MUST provide the formula.
    public abstract double calculateGst(double subtotal);

    // Standard method: Subclasses will INHERIT this automatically.
    public double calculateTotalBill(double subtotal) {
        double gst = calculateGst(subtotal);
        return subtotal + gst;
    }
}