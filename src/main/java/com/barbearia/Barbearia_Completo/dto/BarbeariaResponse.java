package com.barbearia.Barbearia_Completo.dto;


public class BarbeariaResponse {
    private Long id;
    private String name;
    private Boolean active;

    public BarbeariaResponse(Long id, String name, Boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }
}
