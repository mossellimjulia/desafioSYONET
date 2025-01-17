package org.example.api.v1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.domain.model.Cliente;
import org.example.domain.model.Noticia;
import org.example.domain.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "NewsletterController")
public class NewsletterController {


    @Autowired
    private NewsletterService newsletterService;

    @Operation(summary = "Cadastrar cliente", description = "Cadastra um novo cliente no sistema")
    @PostMapping("/clientes")
    public Cliente cadastrarCliente(@Valid @RequestBody Cliente cliente) {
        return newsletterService.cadastrarCliente(cliente);
    }

    @Operation(summary = "Cadastrar notícia", description = "Cadastra uma nova notícia no sistema")
    @PostMapping("/noticias")
    public Noticia cadastrarNoticia(@Valid @RequestBody Noticia noticia) {
        return newsletterService.cadastrarNoticia(noticia);
    }

    @Operation(summary = "Listar todos os clientes", description = "Retorna lista de todos os clientes")
    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return newsletterService.listarClientes();
    }

    @Operation(summary = "Listar todas as notícias", description = "Retorna lista de todas as notícias")
    @GetMapping("/noticias")
    public List<Noticia> listarNoticias() {
        return newsletterService.listarNoticias();
    }
}
