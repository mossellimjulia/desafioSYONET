package org.example.config;

import jakarta.mail.MessagingException;
import org.example.domain.service.NewsletterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NewsletterSchedulerTest {

    @Mock
    private NewsletterService newsletterService;

    @InjectMocks
    private NewsletterScheduler newsletterScheduler;

    @BeforeEach
    void setUp() {
        ScheduledAnnotationBeanPostProcessor scheduler = new ScheduledAnnotationBeanPostProcessor();
        scheduler.postProcessBeforeInitialization(newsletterScheduler, "newsletterScheduler");
    }

    @Test
    void deveExecutarEnviaNewsletter() throws MessagingException {

        newsletterScheduler.enviarNewsletterDiaria();

        verify(newsletterService, times(1)).enviarNewsletter();
    }
}
