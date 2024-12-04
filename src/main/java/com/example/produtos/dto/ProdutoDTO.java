package com.example.produtos.dto;

import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
}