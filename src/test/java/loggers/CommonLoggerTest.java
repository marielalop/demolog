package loggers;

import com.example.springbootdemo.SpringBootDemoApplication;
import config.ConfigProperties;
import enums.EMessage;
import interfaces.ILogger;
import loggers.handlers.HandlerConsole;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.MessagesText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class CommonLoggerTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Autowired
	private ConfigProperties configProperties;

	@Before
	public final void baseSetUp() {}
	
	@After
	public final void baseTearDown() {}

	public static final String MESSAGE = "Test";
	
	@Test
    public void logMessage_loggersEmpty() throws Exception {
		CommonLogger commonLogger = new CommonLogger(configProperties);
		List<ILogger> logger = Collections.EMPTY_LIST;
		commonLogger.setLoggers(logger);
		exception.expectMessage(MessagesText.NO_LOGGER_DEFINED_YET);
		commonLogger.logMessage(MESSAGE, EMessage.ERROR);

    }

	@Test
	public void logMessage_emptyMessage() throws Exception {
		CommonLogger commonLogger = new CommonLogger(configProperties);
		exception.expectMessage(MessagesText.MESSAGE_MUST_BE_SPECIFIED);
		commonLogger.logMessage(null, EMessage.ERROR);
	}

	@Test
	public void logMessage_checkMessageLevelError() throws Exception {
		CommonLogger commonLogger = new CommonLogger(configProperties);
		exception.expectMessage(MessagesText.ERRORS_ARE_NOT_ALLOWED_TO_BE_LOG);
		commonLogger.logMessage(MESSAGE, EMessage.ERROR);
	}

	@Test
	public void logMessage_checkMessageLevelMessage() throws Exception {
		CommonLogger commonLogger = new CommonLogger(configProperties);
		exception.expectMessage(MessagesText.MESSAGE_ARE_NOT_ALLOWED_TO_BE_LOG);
		commonLogger.logMessage(MESSAGE, EMessage.MESSAGE);
	}

	@Test
	public void logMessage_checkMessageLevelWarning() throws Exception {
		CommonLogger commonLogger = new CommonLogger(configProperties);
		exception.expectMessage(MessagesText.WARNINGS_ARE_NOT_ALLOWED_TO_BE_LOG);
		commonLogger.logMessage(MESSAGE, EMessage.WARNING);
	}
	
}
