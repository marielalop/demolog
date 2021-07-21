package loggers.handlers;

import config.ConfigProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;

@Component
@AllArgsConstructor
public class HandlerConsole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static HandlerConsole instance = null;
	
	private HandlerConsole(ConfigProperties configuration) {
		super();
	}
	
	public static synchronized HandlerConsole getInstance(ConfigProperties configuration) {
		if (instance == null) {
			instance = new HandlerConsole(configuration);
		}
		return instance;
	}
	
	public ConsoleHandler getConsoleHandler() {
		return new ConsoleHandler();
	}

}
