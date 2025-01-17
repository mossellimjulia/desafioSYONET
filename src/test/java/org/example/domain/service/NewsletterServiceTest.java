package org.example.domain.service;

import jakarta.mail.MessagingException;
import org.example.domain.model.Cliente;
import org.example.domain.model.Noticia;
import org.example.domain.repository.ClienteRepository;
import org.example.domain.repository.NoticiaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewsletterServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private NoticiaRepository noticiaRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NewsletterService newsletterService;

    @Test
    void deveCadastrarClienteComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setEmail("joao@exemplo.com");

        when(clienteRepository.existsByEmail(cliente.getEmail())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = newsletterService.cadastrarCliente(cliente);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        Mockito.verify(clienteRepository, Mockito.times(1)).save(cliente);
    }

    @Test
    void deveEnviarNewsletterComSucesso() throws MessagingException {
        Cliente cliente = new Cliente();
        cliente.setNome("Eduarda");
        cliente.setEmail("eduarda@exemplo.com");

        Noticia noticia = new Noticia();
        noticia.setTitulo("Título da Notícia");
        noticia.setDescricao("Descrição da notícia.");
        noticia.setProcessada(false);

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(noticiaRepository.findByProcessadaFalse()).thenReturn(List.of(noticia));

        newsletterService.enviarNewsletter();

        Mockito.verify(emailService, Mockito.times(1)).enviarEmail(
                Mockito.eq("eduarda@exemplo.com"),
                Mockito.eq("Notícias do dia!"),
                Mockito.anyString()
        );

        assertTrue(noticia.isProcessada());
        Mockito.verify(noticiaRepository, Mockito.times(1)).saveAll(Mockito.anyList());
    }
}
