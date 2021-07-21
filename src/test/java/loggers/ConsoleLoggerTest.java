package loggers;

import com.example.springbootdemo.SpringBootDemoApplication;
import config.ConfigProperties;
import enums.EMessage;
import interfaces.ILogger;
import loggers.handlers.HandlerConsole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class ConsoleLoggerTest {

	@Autowired
	private ConfigProperties configProperties;

	@Before
	public final void baseSetUp() {}
	
	@After
	public final void baseTearDown() {}
	
	@Test
    public void handlerValid() {
		HandlerConsole consoleManager = HandlerConsole.getInstance(configProperties);
		assertNotNull(consoleManager.getConsoleHandler());
    }
	
	@Test
    public void logMessage() throws Exception {
		ILogger logger = new ConsoleLogger(configProperties);
		logger.logMessage("Test Console - Message Info ", EMessage.MESSAGE);
        assertTrue(true);
    }
	
}
