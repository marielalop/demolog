package loggers.handlers;

import domain.MessageLog;
import enums.EMessage;
import exceptions.MessageLogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.MessageLogRepository;
import utils.MessagesText;

import java.io.Serializable;

@Component
@Service
@Transactional
public class HandlerDatabase implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private final MessageLogRepository messageLogRepository;

	private static HandlerDatabase instance = null;

	public HandlerDatabase(MessageLogRepository messageLogRepository) {
		this.messageLogRepository = messageLogRepository;
	}

	public void write(String message, EMessage messageType) throws MessageLogException {
		MessageLog messageLog = new MessageLog(message, messageType.getValue());
		try{
			messageLogRepository.save(messageLog);
		}catch (Exception ex){
			throw new MessageLogException(MessagesText.ERROR_DURING_MESSAGE_SAVE_TO_DATABASE);
		}

	}
	

}
