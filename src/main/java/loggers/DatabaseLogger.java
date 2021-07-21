package loggers;

import exceptions.MessageLogException;
import loggers.handlers.HandlerDatabase;
import enums.EMessage;
import interfaces.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLogger implements ILogger {

	@Autowired
	private static HandlerDatabase databaseHandler;
	
	public DatabaseLogger() {
	}
	
	@Override
	public void logMessage(String message, EMessage messageType) throws MessageLogException {
		this.databaseHandler.write(message, messageType);
	}
}