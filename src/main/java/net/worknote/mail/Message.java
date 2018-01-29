package net.worknote.mail;

public class Message {

    private String MessageForConfirmationOfMail;

    public Message() {
    }

    public String getMessageForConfirmationOfMail() {
        return MessageForConfirmationOfMail;
    }

    public void setMessageForConfirmationOfMail(String messageForConfirmationOfMail) {
        MessageForConfirmationOfMail = messageForConfirmationOfMail;
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageForConfirmationOfMail='" + MessageForConfirmationOfMail + '\'' +
                '}';
    }
}
