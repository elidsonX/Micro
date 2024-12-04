package com.example.produtos.controller;

import com.example.produtos.dto.EstoqueDTO;
import com.example.produtos.model.Estoque;
import com.example.produtos.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PutMapping("/{produtoId}")
    public ResponseEntity<Estoque> atualizarEstoque(@PathVariable Long produtoId, @Valid @RequestBody EstoqueDTO estoqueDTO) {
        return ResponseEntity.ok(estoqueService.atualizarEstoque(produtoId, estoqueDTO));
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Estoque> buscarEstoque(@PathVariable Long produtoId) {
        return ResponseEntity.ok(estoqueService.buscarEstoque(produtoId));
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarEstoques() {
        return ResponseEntity.ok(estoqueService.listarEstoques());
    }
}