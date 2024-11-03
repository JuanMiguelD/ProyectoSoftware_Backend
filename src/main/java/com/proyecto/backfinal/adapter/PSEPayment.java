package com.proyecto.backfinal.adapter;

public class PSEPayment implements  PaymentMethod {

    private String bankCode;
    private String accountHolder;

    public PSEPayment(String bankCode, String accountHolder) {
        this.bankCode = bankCode;
        this.accountHolder = accountHolder;
    }

    @Override
    public boolean processPayment(double amount) {
        // Lógica real de procesamiento PSE
        System.out.println("Iniciando transferencia PSE desde banco: " + bankCode);
        return processPayment(amount);
    }

    @Override
    public String getPaymentMethod() {
        return "PSE";
    }

    @Override
    public String getTransactionDetails() {
        return String.format("Transferencia PSE - Banco: %s, Titular: %s", bankCode, accountHolder);
    }
}
