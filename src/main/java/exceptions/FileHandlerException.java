package exceptions;

public class FileHandlerException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FileHandlerException(String message) {
        super(message);
    }
	
	public FileHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

}
