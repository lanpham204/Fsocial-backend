package com.fsocial.controllers;

import com.fsocial.dtos.ChatRoomDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.responses.ChatRoomResponse;
import com.fsocial.services.ChatRoomServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/chat_rooms")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomServiceImp chatRoomService;

    @PostMapping
    public ResponseEntity<ChatRoomResponse> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) throws DataNotFoundException {
            ChatRoomResponse chatRoomResponse = chatRoomService.createChatRoom(chatRoomDTO);
            return new ResponseEntity<>(chatRoomResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoomResponse> getChatRoomById(@PathVariable String id) throws DataNotFoundException {
            ChatRoomResponse chatRoomResponse = chatRoomService.getChatRoomById(id);
            return new ResponseEntity<>(chatRoomResponse, HttpStatus.OK);
    }

    @PutMapping("/{chatRoomId}/messages")
    public ResponseEntity<ChatRoomResponse> addMessageToChatRoom(@PathVariable String chatRoomId, @RequestBody ChatRoomDTO chatRoomDTO) throws DataNotFoundException {
            ChatRoomResponse chatRoomResponse = chatRoomService.addMessageToChatRoom(chatRoomId, chatRoomDTO);
            return new ResponseEntity<>(chatRoomResponse, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatRoomResponse>> getChatRoomsByUserId(@PathVariable String userId) throws DataNotFoundException {
            List<ChatRoomResponse> chatRoomResponses = chatRoomService.getChatRoomByUserId(userId);
            return new ResponseEntity<>(chatRoomResponses, HttpStatus.OK);
        }
}
