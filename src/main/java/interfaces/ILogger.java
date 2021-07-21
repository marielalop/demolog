package interfaces;

import enums.EMessage;

import java.util.logging.Logger;

public interface ILogger {
	
	public static final Logger logger = Logger.getLogger("ILogger");
	
	public void logMessage(String message, EMessage messageType) throws Exception;

}
