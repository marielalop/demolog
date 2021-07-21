package config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "log")
public class ConfigProperties {

    // LOG types
    private boolean logMessage;

    private boolean logWarning;

    private boolean logError;

    // LOG loggers.handlers
    private boolean logToFile;

    private String logFilePath;

    private boolean logToDatabase;

    private boolean logToConsole;
}
