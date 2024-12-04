package com.example.produtos.dto;

import lombok.Data;
import java.util.List;

@Data
public class VendaDTO {
    private List<ItemVendaDTO> itens;

    @Data
    public static class ItemVendaDTO {
        private Long produtoId;
        private Integer quantidade;
    }
}