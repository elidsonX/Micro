package com.example.produtos.model;

import javax.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime dataVenda;
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    
    @Column(nullable = false)
    private Double valorTotal;
}