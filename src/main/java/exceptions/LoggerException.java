package exceptions;

public class LoggerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public LoggerException(String message) {
        super(message);
    }

}
