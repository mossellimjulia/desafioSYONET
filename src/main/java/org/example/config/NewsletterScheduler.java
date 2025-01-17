package org.example.config;

import jakarta.mail.MessagingException;
import org.example.domain.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsletterScheduler {

    @Autowired
    private NewsletterService newsletterService;

    @Scheduled(cron = "0 0 8 * * MON-FRI")
    public void enviarNewsletterDiaria() throws MessagingException {
        newsletterService.enviarNewsletter();

    }
}
