package com.proyecto.backfinal.adapter;

public class CreditCardPayment implements  PaymentMethod {
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expiryDate;
    }

    @Override
    public boolean processPayment(double amount) {
        // TODO Auto-generated method stub
        System.out.println("Procesando pago con tarjeta: " + maskCardNumber(cardNumber));  
        return true;  
    }
    @Override
    public String getPaymentMethod() {
        // TODO Auto-generated method stub
        return String.format("Pago con tarjeta terminada en %s", cardNumber.substring(cardNumber.length() - 4));
    }
    @Override
    public String getTransactionDetails() {
        // TODO Auto-generated method stub
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    private String maskCardNumber(String cardNumber) {
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
    
    
}
