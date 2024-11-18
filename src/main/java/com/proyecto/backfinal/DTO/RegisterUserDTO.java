// Juan Miguel Dimaté 0000282752 
// Andrey Esteban Conejo 0000281295 
// Carlos Bello 0000272648 

package com.proyecto.backfinal.DTO;

import lombok.Getter;
import lombok.Setter;

public class RegisterUserDTO {
    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private String email;
    
    @Getter @Setter
    private String password;
    
    @Getter @Setter
    private String userType; 
}
