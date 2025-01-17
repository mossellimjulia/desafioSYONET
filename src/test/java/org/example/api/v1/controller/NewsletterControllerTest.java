package org.example.api.v1.controller;

import org.example.domain.model.Cliente;
import org.example.domain.service.NewsletterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsletterController.class)
public class NewsletterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsletterService newsletterService;


    @Test
    void deveCadastrarClientes() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Aline");
        cliente.setEmail("aline@exemplo.com");

        Mockito.when(newsletterService.cadastrarCliente(Mockito.any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Aline\", \"email\": \"aline@exemplo.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Aline"))
                .andExpect(jsonPath("$.email").value("aline@exemplo.com"));
    }

    @Test
    void deveListarClientes() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Felipe");
        cliente.setEmail("felipe@exemplo.com");

        Mockito.when(newsletterService.listarClientes()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Felipe"))
                .andExpect(jsonPath("$[0].email").value("felipe@exemplo.com"));
    }
}
