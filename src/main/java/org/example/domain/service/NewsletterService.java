package org.example.domain.service;

import jakarta.mail.MessagingException;
import org.example.domain.exceptions.DadosInvalidosException;
import org.example.domain.exceptions.EmailJaCadastradoException;
import org.example.domain.model.Cliente;
import org.example.domain.model.Noticia;
import org.example.domain.repository.ClienteRepository;
import org.example.domain.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class NewsletterService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private EmailService emailService;

    public Cliente cadastrarCliente(Cliente cliente) {
        if (cliente.getNome().isBlank()) {
            throw new DadosInvalidosException("O campo 'nome' não pode estar vazio.");
        }
        if (cliente.getEmail().isBlank()) {
            throw new DadosInvalidosException("O campo 'email' não pode estar vazio.");
        }

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new EmailJaCadastradoException("O email " + cliente.getEmail() + " já está cadastrado.");
        }

        return clienteRepository.save(cliente);
    }

    public Noticia cadastrarNoticia(Noticia noticia) {
        if (noticia.getTitulo().isBlank()) {
            throw new DadosInvalidosException("O campo 'titulo' da notícia não pode estar vazio.");
        }
        if (noticia.getDescricao().isBlank()) {
            throw new DadosInvalidosException("O campo 'descricao' da notícia não pode estar vazio.");
        }
        noticia.setProcessada(false);
        return noticiaRepository.save(noticia);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public List<Noticia> listarNoticias() {
        return noticiaRepository.findAll();
    }

    public void enviarNewsletter() throws MessagingException {
        List<Cliente> clientes = clienteRepository.findAll();

        List<Noticia> noticias = noticiaRepository.findByProcessadaFalse();

        Collections.shuffle(noticias);

        List<Noticia> noticiasEnviadas = noticias.subList(0, Math.min(2, noticias.size())); // Seleciona no máximo 2 notícias

        for (Cliente cliente : clientes) {

            StringBuilder corpo = new StringBuilder("<html><body>");
            corpo.append("<p>Bom dia ").append(cliente.getNome()).append("!</p>");

            if (cliente.getNascimento() != null && cliente.getNascimento().equals(LocalDate.now())) {
                corpo.append("<p><b>Feliz aniversário!</b></p>");
            }

            corpo.append("<p>Segue as notícias de hoje:</p>");

            for (Noticia noticia : noticiasEnviadas) {
                corpo.append("<p><b><a href='").append(noticia.getLink()).append("'>")
                        .append(noticia.getTitulo())
                        .append("</a></b></p>");
                corpo.append("<p>").append(noticia.getDescricao()).append("</p>");
            }

            corpo.append("<p>Até a próxima!</p>");
            corpo.append("</body></html>");

            try {
                emailService.enviarEmail(cliente.getEmail(), "Notícias do dia!", corpo.toString());
            } catch (MessagingException e) {
                System.err.println("Erro ao enviar o e-mail para " + cliente.getEmail() + ": " + e.getMessage());
            }
        }

        noticias.forEach(n -> n.setProcessada(true));
        noticiaRepository.saveAll(noticias);
    }
}













