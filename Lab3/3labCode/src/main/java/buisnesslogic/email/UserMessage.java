package buisnesslogic.email;

public class UserMessage {
    public String toAddr;
    public String messageText;
    public String errorText;

    public UserMessage() {
    }

    public UserMessage(String toAddr) {
        this.toAddr = toAddr;
    }

    public UserMessage(String toAddr, String messageText, String errorText) {
        this.toAddr = toAddr;
        this.messageText = messageText;
        this.errorText = errorText;
    }

    public String getToAddr() {
        return toAddr;
    }

    public void setToAddr(String toAddr) {
        this.toAddr = toAddr;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
