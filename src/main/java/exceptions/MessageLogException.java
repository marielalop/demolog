package exceptions;

public class MessageLogException extends Exception {

    public MessageLogException() {
        super();
    }

    public MessageLogException(String message) {
        super(message);
    }

    public MessageLogException(String message, Throwable cause) {
        super(message, cause);
    }

}
