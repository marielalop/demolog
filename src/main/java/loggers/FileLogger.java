package loggers;

import config.ConfigProperties;
import loggers.handlers.HandlerFile;
import enums.EMessage;
import interfaces.ILogger;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
public class FileLogger implements ILogger {

	private static HandlerFile fileHandler;
	
	public FileLogger(ConfigProperties configuration) {
		this.fileHandler = new HandlerFile(configuration);
		logger.addHandler(this.fileHandler.getFileHandler());
	}

	@Override
	public void logMessage(String message, EMessage messageType) {
		logger.log(Level.SEVERE, message);
	}
}
