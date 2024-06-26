package com.fsocial.controllers;

import com.fsocial.models.ChatMessage;
import com.fsocial.models.ChatRoom;
import com.fsocial.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/chat")
public class ChatController {

//    @Autowired
//    private ChatService chatService;
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public ChatMessage sendMessage(ChatMessage chatMessage) {
//        // Convert ChatMessage to Message entity and save to the chat room
////        Message message = new Message(chatMessage.getContent());
////        chatService.addMessageToChatRoom(chatMessage.getSender(), chatMessage.getReceiver(), message);
//        return chatMessage;
//    }
//
//    @PostMapping("/send")
//    public ChatMessage sendChatMessage(@RequestBody ChatMessage chatMessage) {
//        // Convert ChatMessage to Message entity and save to the chat room
//        Message message = new Message(chatMessage.getContent());
////        chatService.addMessageToChatRoom(chatMessage.getSender(), chatMessage.getReceiver(), message);
//        return chatMessage;
//    }

//    @GetMapping("/messages/{chatRoomId}")
//    public List<Message> getMessages(@PathVariable String chatRoomId) {
//        return chatService.getMessagesFromChatRoom(chatRoomId);
//    }
}
