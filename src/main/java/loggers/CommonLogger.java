package loggers;

import config.ConfigProperties;
import enums.EMessage;
import exceptions.LoggerException;
import exceptions.MessageException;
import interfaces.ILogger;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import utils.MessagesText;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Data
public class CommonLogger implements ILogger {

    private List<ILogger> loggers = new ArrayList<>();

    private final ConfigProperties config;

    public CommonLogger(ConfigProperties config) {
        this.config = config;

        if (this.config.isLogToDatabase()){
            this.loggers.add(new DatabaseLogger());
        }

        if (this.config.isLogToConsole()){
            this.loggers.add(new ConsoleLogger(this.config));
        }

        if (this.config.isLogToFile()){
            this.loggers.add(new FileLogger(this.config));
        }

    }

    @Override
    public void logMessage(String message, EMessage messageType) throws MessageException, LoggerException{
        if (loggers.isEmpty())
            throw new LoggerException(MessagesText.NO_LOGGER_DEFINED_YET);

        if (Strings.isBlank(message)) {
            throw new MessageException(MessagesText.MESSAGE_MUST_BE_SPECIFIED);
        }

        checkMessageLevel(messageType);

        String finalMessage = messageType.getName() + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + message;
        loggers.forEach(item -> {
            try {
                item.logMessage(finalMessage, messageType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }

    private void checkMessageLevel(EMessage messageType) throws LoggerException {
        if (EMessage.MESSAGE.equals(messageType) && !this.config.isLogMessage()) {
            throw new LoggerException(MessagesText.MESSAGE_ARE_NOT_ALLOWED_TO_BE_LOG);
        } else if (EMessage.WARNING.equals(messageType) && !this.config.isLogWarning()) {
            throw new LoggerException(MessagesText.WARNINGS_ARE_NOT_ALLOWED_TO_BE_LOG);
        } else if (EMessage.ERROR.equals(messageType) && !this.config.isLogError()) {
            throw new LoggerException(MessagesText.ERRORS_ARE_NOT_ALLOWED_TO_BE_LOG);
        }

    }
}
