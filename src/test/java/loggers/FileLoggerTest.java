package loggers;

import com.example.springbootdemo.SpringBootDemoApplication;
import config.ConfigProperties;
import enums.EMessage;
import interfaces.ILogger;
import loggers.handlers.HandlerFile;
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
public class FileLoggerTest {

	@Autowired
	private ConfigProperties configProperties;

	@Before
	public final void baseSetUp() {}
	
	@After
	public final void baseTearDown() {}
	
	@Test
    public void handlerValid() {
		HandlerFile handlerFile = new HandlerFile(configProperties);
		assertNotNull(handlerFile.getFileHandler());
    }
	
	@Test
    public void logMessage() throws Exception {
		ILogger logger = new FileLogger(configProperties);
		logger.logMessage("Test - Message Info ", EMessage.MESSAGE);
        assertTrue(true);
    }
	
}
