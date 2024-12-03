package com.example.produtos.service;

import com.example.produtos.dto.ProdutoDTO;
import com.example.produtos.model.Produto;
import com.example.produtos.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarProduto_DeveRetornarProdutoCriado() {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome("Produto Teste");
        dto.setDescricao("Descrição Teste");
        dto.setPreco(10.0);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(1L);
        produtoSalvo.setNome(dto.getNome());
        produtoSalvo.setDescricao(dto.getDescricao());
        produtoSalvo.setPreco(dto.getPreco());
        produtoSalvo.setQuantidade(0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        Produto resultado = produtoService.criarProduto(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals(dto.getNome(), resultado.getNome());
        assertEquals(dto.getDescricao(), resultado.getDescricao());
        assertEquals(dto.getPreco(), resultado.getPreco());
        assertEquals(0, resultado.getQuantidade());

        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    void buscarProduto_QuandoProdutoExiste_DeveRetornarProduto() {
        Long id = 1L;
        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome("Produto Existente");

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        Produto resultado = produtoService.buscarProduto(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Produto Existente", resultado.getNome());

        verify(produtoRepository, times(1)).findById(id);
    }

    @Test
    void buscarProduto_QuandoProdutoNaoExiste_DeveLancarExcecao() {
        Long id = 1L;
        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> produtoService.buscarProduto(id));

        verify(produtoRepository, times(1)).findById(id);
    }

    // Adicione mais testes para outros métodos do service
}