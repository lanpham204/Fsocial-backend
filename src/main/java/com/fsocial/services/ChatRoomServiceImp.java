package com.fsocial.services;

import com.fsocial.dtos.ChatRoomDTO;
import com.fsocial.exceptions.DataNotFoundException;
import com.fsocial.models.ChatRoom;
import com.fsocial.models.Message;
import com.fsocial.models.User;
import com.fsocial.repositories.ChatRoomRepository;
import com.fsocial.repositories.UserRepository;
import com.fsocial.responses.ChatRoomResponse;
import com.fsocial.responses.UserResponse;
import com.fsocial.services.interfaces.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImp implements ChatRoomService {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ModelMapper modelMapper;
    @Override
    public ChatRoomResponse createChatRoom(ChatRoomDTO chatRoomDTO) throws DataNotFoundException {
        User sender = userRepository.findById(chatRoomDTO.getSenderId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + chatRoomDTO.getSenderId()));
        User receiver = userRepository.findById(chatRoomDTO.getReceiverId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + chatRoomDTO.getReceiverId()));
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setMessage(chatRoomDTO.getMessage());
        messages.add(message);
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
                        .sender(sender)
                        .messages(messages)
                        .receiver(receiver)
                        .build());
        return ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .sender(modelMapper.map(chatRoom.getSender(), UserResponse.class))
                .receiver(modelMapper.map(chatRoom.getReceiver(), UserResponse.class))
                .messages(chatRoom.getMessages())
                .build();
    }

    @Override
    public ChatRoomResponse getChatRoomById(String id) throws DataNotFoundException {
        ChatRoom chatRoom = chatRoomRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Cannot found chat room with id: " + id));
        return ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .sender(modelMapper.map(chatRoom.getSender(), UserResponse.class))
                .receiver(modelMapper.map(chatRoom.getReceiver(), UserResponse.class))
                .messages(chatRoom.getMessages())
                .build();
    }

    @Override
    public ChatRoomResponse addMessageToChatRoom(String chatRoomId, ChatRoomDTO chatRoomDTO) throws DataNotFoundException {
        User sender = userRepository.findById(chatRoomDTO.getSenderId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + chatRoomDTO.getSenderId()));
        User receiver = userRepository.findById(chatRoomDTO.getReceiverId()).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + chatRoomDTO.getReceiverId()));
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new DataNotFoundException("Cannot found chat room with id: " + chatRoomId));
        List<Message> messages = chatRoom.getMessages();
        Message message = new Message();
        message.setMessage(chatRoomDTO.getMessage());
        messages.add(message);
        chatRoom.setMessages(messages);
        chatRoom.setReceiver(receiver);
        chatRoom.setSender(sender);
        ChatRoom updatedChatRoom = chatRoomRepository.save(chatRoom);
        return ChatRoomResponse.builder()
                .id(updatedChatRoom.getId())
                .sender(modelMapper.map(updatedChatRoom.getSender(), UserResponse.class))
                .receiver(modelMapper.map(updatedChatRoom.getReceiver(), UserResponse.class))
                .messages(updatedChatRoom.getMessages())
                .build();
    }

    @Override
    public List<ChatRoomResponse> getChatRoomByUserId(String userId) throws DataNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("Cannot found user with id: " + userId));
        List<ChatRoom> chatRooms = chatRoomRepository.findChatRoomBySenderIdOrReceiverId(user.getId(), user.getId());
        return chatRooms.stream().map(chatRoom ->  ChatRoomResponse.builder()
                .id(chatRoom.getId())
                .sender(modelMapper.map(chatRoom.getSender(), UserResponse.class))
                .receiver(modelMapper.map(chatRoom.getReceiver(), UserResponse.class))
                .messages(chatRoom.getMessages())
                .build()).toList();
    }
}
