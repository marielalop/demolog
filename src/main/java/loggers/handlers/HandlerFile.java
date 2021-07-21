package loggers.handlers;

import config.ConfigProperties;
import exceptions.FileHandlerException;
import exceptions.LogFileException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import utils.MessagesText;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;

@Component
@AllArgsConstructor
public class HandlerFile implements Serializable {

	private static final long serialVersionUID = 1L;

	private final ConfigProperties config;
	
	public java.io.File getLogFile() {
		File logFile = new java.io.File(this.config.getLogFilePath());
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				throw new LogFileException(MessagesText.ERROR_CREATING_NEW_LOG_FILE, e);
			}
		}
		return logFile;
	}
	
	public FileHandler getFileHandler() {
		try {
			getLogFile();
			return new java.util.logging.FileHandler(this.config.getLogFilePath());
		} catch (SecurityException | IOException e) {
			throw new FileHandlerException("Error get file handler.", e);
		}
	}

}
