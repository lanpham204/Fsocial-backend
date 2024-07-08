package com.fsocial.controllers;

import com.fsocial.dtos.ChatRoomDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.ChatRoomResponse;
import com.fsocial.services.interfaces.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebsocketController {
    private final ChatRoomService chatRoomService;
    @MessageMapping("/chat/{chatRoomId}")
    @SendTo("/topic/{chatRoomId}")
    public ChatRoomResponse chat(@DestinationVariable String chatRoomId, ChatRoomDTO chatRoomDTO) throws DataNotFoundException {
        return chatRoomService.addMessageToChatRoom(chatRoomId,chatRoomDTO);
    }
}
