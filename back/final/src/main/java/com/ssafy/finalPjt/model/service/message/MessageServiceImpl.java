package com.ssafy.finalPjt.model.service.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.finalPjt.model.dao.MessageDao;
import com.ssafy.finalPjt.model.dto.Message;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	MessageDao messageDao;
	
	@Override
	public int registMessage(Message message) {
		return messageDao.createMessage(message);
	}

	@Override
	public List<Message> getReceivedMessage(String id) {
		return messageDao.receivedMessage(id);
	}

	@Override
	public List<Message> getSentMessage(String id) {
		return messageDao.sentMessage(id);
	}

}
