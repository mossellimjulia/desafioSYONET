package org.example.domain.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void deveEnviarEmailComSucesso() throws MessagingException {

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);

        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        doNothing().when(javaMailSender).send(mimeMessage);

        emailService.enviarEmail("teste@exemplo.com", "Teste Subject", "Teste Body");

        verify(javaMailSender, times(1)).send(mimeMessage);
    }

}