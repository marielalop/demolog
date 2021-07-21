package loggers;

import config.ConfigProperties;
import loggers.handlers.HandlerConsole;
import enums.EMessage;
import exceptions.LoggerException;
import exceptions.MessageException;
import interfaces.ILogger;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Component
public class ConsoleLogger implements ILogger {

    private static HandlerConsole consoleHandler;

    public ConsoleLogger(ConfigProperties configuration) {
        this.consoleHandler = HandlerConsole.getInstance(configuration);
        logger.addHandler(this.consoleHandler.getConsoleHandler());
    }

    @Override
    public void logMessage(String message, EMessage messageType) throws MessageException, LoggerException{
        logger.log(Level.INFO, message);

    }

}
