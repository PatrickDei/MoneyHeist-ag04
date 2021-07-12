package org.agency04.software.moneyheist.service.email;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
