package org.agency04.software.moneyheist.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
