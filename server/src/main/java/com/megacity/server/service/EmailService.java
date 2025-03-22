package com.megacity.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;

import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends a simple text-based email.
     *
     * @param to      Recipient email address
     * @param subject Email subject
     * @param text    Email body
     */
    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            logger.info("Email sent successfully to {}", to);
        } catch (Exception e) {
            logger.error("Failed to send email to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send email", e);
        }
    }

    /**
     * Sends an HTML-based email.
     *
     * @param to      Recipient email address
     * @param subject Email subject
     * @param html    HTML content of the email
     */
    public void sendHtmlEmail(String to, String subject, String html) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true); // Set to true to enable HTML content
            mailSender.send(mimeMessage);
            logger.info("HTML email sent successfully to {}", to);
        } catch (MessagingException e) {
            logger.error("Failed to send HTML email to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send HTML email", e);
        }
    }

    /**
     * Sends an email with attachments.
     *
     * @param to          Recipient email address
     * @param subject     Email subject
     * @param text        Email body
     * @param attachments Map of file names to byte arrays (file content)
     */
    public void sendEmailWithAttachments(String to, String subject, String text, Map<String, byte[]> attachments) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true); // true indicates multipart message
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            // Add attachments
            for (Map.Entry<String, byte[]> entry : attachments.entrySet()) {
                helper.addAttachment(entry.getKey(), new ByteArrayResource(entry.getValue()));
            }

            mailSender.send(mimeMessage);
            logger.info("Email with attachments sent successfully to {}", to);
        } catch (MessagingException e) {
            logger.error("Failed to send email with attachments to {}: {}", to, e.getMessage(), e);
            throw new RuntimeException("Failed to send email with attachments", e);
        }
    }

    /**
     * Sends an email to multiple recipients.
     *
     * @param to      List of recipient email addresses
     * @param subject Email subject
     * @param text    Email body
     */
    public void sendEmailToMultipleRecipients(List<String> to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to.toArray(new String[0]));
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            logger.info("Email sent successfully to multiple recipients: {}", to);
        } catch (Exception e) {
            logger.error("Failed to send email to multiple recipients: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send email to multiple recipients", e);
        }
    }
}