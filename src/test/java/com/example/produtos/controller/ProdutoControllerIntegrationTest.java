package com.example.produtos.controller;

import com.example.produtos.dto.ProdutoDTO;
import com.example.produtos.model.Produto;
import com.example.produtos.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    void tearDown() {
        produtoRepository.deleteAll();
    }

    @Test
    void criarProduto_DeveRetornarProdutoCriado() throws Exception {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setNome("Novo Produto");
        dto.setDescricao("Descrição do Novo Produto");
        dto.setPreco(15.99);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.descricao").value(dto.getDescricao()))
                .andExpect(jsonPath("$.preco").value(dto.getPreco()))
                .andExpect(jsonPath("$.quantidade").value(0));
    }

    @Test
    void buscarProduto_QuandoProdutoExiste_DeveRetornarProduto() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição Teste");
        produto.setPreco(10.0);
        produto.setQuantidade(5);
        produto = produtoRepository.save(produto);

        mockMvc.perform(get("/api/produtos/{id}", produto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(produto.getId()))
                .andExpect(jsonPath("$.nome").value(produto.getNome()))
                .andExpect(jsonPath("$.descricao").value(produto.getDescricao()))
                .andExpect(jsonPath("$.preco").value(produto.getPreco()))
                .andExpect(jsonPath("$.quantidade").value(produto.getQuantidade()));
    }

    // Adicione mais testes para outros endpoints
}