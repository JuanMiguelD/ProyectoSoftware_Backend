package com.proyecto.backfinal.adapter;

public class CreditCardPayment implements  PaymentMethod {
    private String cardNumber;
    @SuppressWarnings("unused")
    private String expirationDate;
    @SuppressWarnings("unused")
    private String cvv;

    public CreditCardPayment(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expiryDate;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Procesando pago con tarjeta: " + maskCardNumber(cardNumber));  
        return true;  
    }
    @Override
    public String getPaymentMethod() {
        return String.format("Pago con tarjeta terminada en %s", cardNumber.substring(cardNumber.length() - 4));
    }
    @Override
    public String getTransactionDetails() {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    private String maskCardNumber(String cardNumber) {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
    
    
}
