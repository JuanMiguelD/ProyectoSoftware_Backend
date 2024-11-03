package com.proyecto.backfinal.adapter;

public interface PaymentMethod {
    boolean processPayment(double amount);
    String getPaymentMethod();
    String getTransactionDetails();
}
