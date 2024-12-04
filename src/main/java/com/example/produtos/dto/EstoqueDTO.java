package com.example.produtos.dto;

import lombok.Data;

@Data
public class EstoqueDTO {
    private Integer quantidade;

    public EstoqueDTO() {}

    public EstoqueDTO(Integer quantidade) {
        this.quantidade = quantidade;
    }
}