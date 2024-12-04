package com.example.produtos.service;

import com.example.produtos.dto.VendaDTO;
import com.example.produtos.dto.EstoqueDTO;
import com.example.produtos.model.Venda;
import com.example.produtos.model.Pedido;
import com.example.produtos.model.Produto;
import com.example.produtos.model.Estoque;
import com.example.produtos.repository.VendaRepository;
import com.example.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueService estoqueService;

    @Transactional
    public Venda realizarVenda(VendaDTO vendaDTO) {
        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        
        List<Pedido> pedidos = new ArrayList<>();
        double valorTotal = 0;

        for (VendaDTO.ItemVendaDTO item : vendaDTO.getItens()) {
            Produto produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
            
            Estoque estoque = estoqueService.buscarEstoque(produto.getId());
            if (estoque.getQuantidade() < item.getQuantidade()) {
                throw new IllegalStateException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            Pedido pedido = new Pedido();
            pedido.setVenda(venda);
            pedido.setProduto(produto);
            pedido.setQuantidade(item.getQuantidade());
            pedido.setPrecoUnitario(produto.getPreco());
            pedidos.add(pedido);

            valorTotal += produto.getPreco() * item.getQuantidade();

            // Atualizar estoque
            EstoqueDTO estoqueDTO = new EstoqueDTO();
            estoqueDTO.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
            estoqueService.atualizarEstoque(produto.getId(), estoqueDTO);
        }

        venda.setPedidos(pedidos);
        venda.setValorTotal(valorTotal);

        return vendaRepository.save(venda);
    }

    public Venda buscarVenda(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada"));
    }

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }
}