package com.example.produtos.service;

import com.example.produtos.dto.EstoqueDTO;
import com.example.produtos.model.Estoque;
import com.example.produtos.model.Produto;
import com.example.produtos.repository.EstoqueRepository;
import com.example.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Estoque atualizarEstoque(Long produtoId, EstoqueDTO estoqueDTO) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId);
        if (estoque == null) {
            estoque = new Estoque();
            estoque.setProduto(produto);
        }
        
        estoque.setQuantidade(estoqueDTO.getQuantidade());
        return estoqueRepository.save(estoque);
    }

    public Estoque buscarEstoque(Long produtoId) {
        return estoqueRepository.findByProdutoId(produtoId);
    }

    public List<Estoque> listarEstoques() {
        return estoqueRepository.findAll();
    }
}