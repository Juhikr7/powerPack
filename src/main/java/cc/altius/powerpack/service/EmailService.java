package cc.altius.powerpack.service;
import cc.altius.powerpack.model.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);
}

