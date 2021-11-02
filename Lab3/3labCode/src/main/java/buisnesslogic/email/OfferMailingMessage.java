package buisnesslogic.email;

public class OfferMailingMessage extends UserMessage {
    public OfferMailingMessage() {
        this.messageText = "Offer text";
        this.errorText = "error text";
    }

    public OfferMailingMessage(String toAddr, String messageText, String errorText) {
        super(toAddr, messageText, errorText);
        this.messageText = messageText;
        this.errorText = errorText;
    }

}
