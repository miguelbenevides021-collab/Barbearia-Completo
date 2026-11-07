package com.barbearia.Barbearia_Completo.dto;

import java.math.BigDecimal;

public class ServicoResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer durationMinutes;
    private Boolean active;

    public ServicoResponse(Long id, String name, BigDecimal price, Integer durationMinutes, Boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.durationMinutes = durationMinutes;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public Boolean getActive() {
        return active;
    }
}
