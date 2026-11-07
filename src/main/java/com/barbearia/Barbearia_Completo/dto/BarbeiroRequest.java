package com.barbearia.Barbearia_Completo.dto;

import jakarta.validation.constraints.NotBlank;


public class BarbeiroRequest {
    @NotBlank private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
