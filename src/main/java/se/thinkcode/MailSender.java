package se.thinkcode;

import java.util.List;

public interface MailSender {
    void sendEmail(List<Borrower> lateBorrowers);
}
