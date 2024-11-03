package com.proyecto.backfinal.adapter;

public class PaypalPayment implements  PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Procesando pago con Paypal ");  
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Paypal";
    }

    @Override
    public String getTransactionDetails() {
        
        return "Tranferencia realizada mediante paypal";
        
    }
    

    
}
