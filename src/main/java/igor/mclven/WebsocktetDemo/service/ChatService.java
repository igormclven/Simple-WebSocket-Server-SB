package igor.mclven.WebsocktetDemo.service;

import igor.mclven.WebsocktetDemo.model.Message;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String sanitizeContent(String content) {
        return content.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    public void logMessage(Message content) {
        System.out.println(content);
    }
}
