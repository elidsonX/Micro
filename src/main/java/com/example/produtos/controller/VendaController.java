package com.example.produtos.controller;

import com.example.produtos.dto.VendaDTO;
import com.example.produtos.model.Venda;
import com.example.produtos.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> realizarVenda(@Valid @RequestBody VendaDTO vendaDTO) {
        return new ResponseEntity<>(vendaService.realizarVenda(vendaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVenda(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscarVenda(id));
    }

    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas() {
        return ResponseEntity.ok(vendaService.listarVendas());
    }
}