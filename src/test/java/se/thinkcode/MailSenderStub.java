package se.thinkcode;

import java.util.ArrayList;
import java.util.List;

public class MailSenderStub implements MailSender {
    private final List<Borrower> mailsSent = new ArrayList<>();

    public List<Borrower> hasSentMail() {
        return this.mailsSent;
    }

    @Override
    public void sendEmail(List<Borrower> lateBorrowers) {
        this.mailsSent.addAll(lateBorrowers);
    }
}
