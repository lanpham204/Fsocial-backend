package com.fsocial.services.interfaces;

import com.fsocial.dtos.ChatRoomDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.ChatRoomResponse;

import java.util.List;

public interface ChatRoomService {
    ChatRoomResponse createChatRoom(ChatRoomDTO chatRoomDTO) throws DataNotFoundException;
    ChatRoomResponse getChatRoomById(String id) throws DataNotFoundException;
    ChatRoomResponse addMessageToChatRoom(String chatRoomId, ChatRoomDTO chatRoomDTO) throws DataNotFoundException;
    List<ChatRoomResponse> getChatRoomByUserId(String userId) throws DataNotFoundException;
}
