// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 

package com.proyecto.backfinal.DTO;

import lombok.Getter;
import lombok.Setter;

public class PurchaseDTO {
    @Getter @Setter
    private Long BookId; // ID del libro que se va a comprar
    
    @Getter @Setter
    private Long UserId; // ID del lector que realiza la compra
    
    @Getter @Setter
    private String PaymentMethod; // medio por el que se realiza el  pago

}

