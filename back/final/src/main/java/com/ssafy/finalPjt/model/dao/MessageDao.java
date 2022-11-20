package com.ssafy.finalPjt.model.dao;

import java.util.List;

import com.ssafy.finalPjt.model.dto.Message;

public interface MessageDao {

	// 메세지 등록
	int createMessage(Message message);

	// 내가 받은 모든 메세지
	List<Message> receivedMessage(String id);
	
	// 내가 보낸 모든 메세지
	List<Message> sentMessage(String id);
}
