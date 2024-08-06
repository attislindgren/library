package se.thinkcode.library;

import java.util.List;

public interface MailSender {
    void sendEmail(List<Borrower> lateBorrowers);
}
