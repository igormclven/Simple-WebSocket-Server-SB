package igor.mclven.WebsocktetDemo.controller;

import igor.mclven.WebsocktetDemo.model.Message;
import igor.mclven.WebsocktetDemo.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message message, Principal principal) {
        message.setSender(principal.getName());
        message.setTimestamp(LocalDateTime.now());
        message.setType(Message.MessageType.CHAT);

        //Sanitizar el contenido del mensaje
        message.setContent(chatService.sanitizeContent(message.getContent()));

        chatService.logMessage(message);

        return message;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor, Principal principal) {

        // Añadir nombre de usuario en la sesión WebSocket
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", principal.getName());
        message.setContent(principal.getName() + " se ha unido al chat!");
        message.setType(Message.MessageType.JOIN);
        message.setTimestamp(LocalDateTime.now());

        return message;
    }
}