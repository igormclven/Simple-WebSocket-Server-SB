package igor.mclven.WebsocktetDemo.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private String id;
    private String content;
    private String sender;
    private LocalDateTime timestamp;
    private MessageType type;

    public enum MessageType {
        CHAT, JOIN, LEAVE
    }

    // Constructor
    public Message() {
        this.timestamp = LocalDateTime.now();
        this.type = MessageType.CHAT;
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return Objects.equals(id, message.id) &&
                Objects.equals(content, message.content) &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(timestamp, message.timestamp) &&
                type == message.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, sender, timestamp, type);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                '}';
    }
}