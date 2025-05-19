package com.SDP.project.services.impli;

import com.SDP.project.models.Order;
import com.SDP.project.models.ProcessedOrder;
import com.SDP.project.models.School;
import com.SDP.project.models.PaperSets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.properties.mail.smtp.from:stjosephsinstitute@example.com}")
    private String fromEmail;

    @Value("${app.name:St. Joseph's Institute}")
    private String appName;

    /**
     * Send order processing notification email to the school
     */
    public void sendOrderProcessedNotification(ProcessedOrder processedOrder, School school, Order order, PaperSets paperSet) {
        try {
            // Ensure school email is not null or empty
            String schoolEmail = school.getEmail();
            if (schoolEmail == null || schoolEmail.trim().isEmpty()) {
                System.err.println("Warning: School email is null or empty. Using fallback email.");
                schoolEmail = "test@example.com"; // Fallback email for testing
            }

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            // Ensure fromEmail is not null or empty
            if (fromEmail == null || fromEmail.trim().isEmpty()) {
                fromEmail = "noreply@stjosephsinstitute.com";
            }

            helper.setFrom(fromEmail);
            helper.setTo(schoolEmail);
            helper.setSubject("Order #" + order.getId() + " Has Been Processed");

            // Create context for template
            Context context = new Context();
            context.setVariable("schoolName", school.getName());
            context.setVariable("orderId", order.getId());
            context.setVariable("paperGrade", paperSet.getGrade());
            context.setVariable("paperCategory", paperSet.getCategory());
            context.setVariable("processedQuantity", processedOrder.getProcessedQuantity());
            context.setVariable("dateProcessed", processedOrder.getDateProcessed());
            context.setVariable("fromPaperNo", processedOrder.getFromPaperNo());
            context.setVariable("toPaperNo", processedOrder.getToPaperNo());
            context.setVariable("sequenceNo", processedOrder.getSequenceNo());
            context.setVariable("appName", appName);

            // For simpler testing, use plain text instead of HTML template if needed
            // helper.setText("Order #" + order.getId() + " has been processed for " + school.getName(), false);

            // Process the email template
            String emailContent = templateEngine.process("email/order-processed", context);
            helper.setText(emailContent, true);

            // Send the email
            mailSender.send(message);
            System.out.println("Email sent successfully to: " + schoolEmail);

        } catch (MessagingException e) {
            // Log the error but don't prevent order processing completion
            System.err.println("Failed to send email notification: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Send a simple test email
     */
    public void sendTestEmail() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);

            helper.setFrom(fromEmail);
            helper.setTo("test@example.com");
            helper.setSubject("Test Email from St. Joseph's Institute");
            helper.setText("This is a test email to verify email functionality is working correctly.");

            mailSender.send(message);
            System.out.println("Test email sent successfully!");

        } catch (MessagingException e) {
            System.err.println("Failed to send test email: " + e.getMessage());
            e.printStackTrace();
        }
    }
}