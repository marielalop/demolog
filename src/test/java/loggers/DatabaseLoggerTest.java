package loggers;

import com.example.springbootdemo.SpringBootDemoApplication;
import domain.MessageLog;
import enums.EMessage;
import exceptions.MessageLogException;
import interfaces.ILogger;
import loggers.handlers.HandlerDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import repository.MessageLogRepository;
import utils.MessagesText;

import javax.persistence.PersistenceException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class DatabaseLoggerTest {

	public static final String MESSAGE = "Test";
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Mock
	private MessageLogRepository messageLogRepository;

	@InjectMocks
	private HandlerDatabase handlerDatabase;
	
	@Before
	public final void baseSetUp() {}
	
	@After
	public final void baseTearDown() {}

	@Test
	public void addMessageLog() throws MessageLogException {
		MessageLog messageLog = mockMessageLog();

		Mockito.when(messageLogRepository.save(Mockito.any())).thenReturn(messageLog);
		handlerDatabase.write(MESSAGE, EMessage.MESSAGE);
		assertEquals(messageLog.getMessage(), MESSAGE);

		Mockito.verify(messageLogRepository, Mockito.times(1)).save(Mockito.any());

	}

	@Test
	public void addMessageLog_whenServiceException() throws MessageLogException {
		MessageLog messageLog = mockMessageLog();

		Mockito.when(messageLogRepository.save(Mockito.any())).thenThrow(new PersistenceException());
		exception.expectMessage(MessagesText.ERROR_DURING_MESSAGE_SAVE_TO_DATABASE);

		handlerDatabase.write(MESSAGE, EMessage.MESSAGE);

		Mockito.verify(messageLogRepository, Mockito.times(1)).save(Mockito.any());

	}

	private MessageLog mockMessageLog() {
		return MessageLog.builder().message(MESSAGE).type(1).build();
	}

}
