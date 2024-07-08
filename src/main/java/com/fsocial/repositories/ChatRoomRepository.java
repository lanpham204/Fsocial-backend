package com.fsocial.repositories;

import com.fsocial.models.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRoomRepository extends MongoRepository<ChatRoom,String> {
    List<ChatRoom> findChatRoomBySenderIdOrReceiverId(String senderId, String receiverId);
}
