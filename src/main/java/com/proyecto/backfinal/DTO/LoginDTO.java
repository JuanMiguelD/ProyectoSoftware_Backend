package com.proyecto.backfinal.DTO;

import lombok.Getter;
import lombok.Setter;

public class LoginDTO {
    @Getter @Setter
    private String email;
    
    @Getter @Setter
    private String password;
}
