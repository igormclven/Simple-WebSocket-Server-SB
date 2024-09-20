
package igor.mclven.WebsocktetDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Registra el endpoint de WebSocket
                .setAllowedOrigins("http://localhost:3000") //Origines permitidos
                .withSockJS()
                .setStreamBytesLimit(512 * 1024) // Limita el tamaño de los paquetes a 512 KB
                .setHttpMessageCacheSize(1000)
                .setDisconnectDelay(30 * 1000); // Tiempo de espera antes de desconectar el cliente
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // Prefijo de destino de la aplicación
        registry.enableSimpleBroker("/topic", "/queue")
                .setHeartbeatValue(new long[]{10000, 10000}) // Intervalo de heartbeat en milisegundos
                .setTaskScheduler(taskScheduler());
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(128 * 1024) // Limita el tamaño de los paquetes a 128 KB
                .setSendTimeLimit(20 * 1000) // Límite de tiempo de envío en milisegundos
                .setSendBufferSizeLimit(512 * 1024); // Límite de tamaño de buffer de envío en bytes
    }

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2); // Número de hilos de ejecución
        scheduler.setThreadNamePrefix("websocket-heartbeat-thread-"); // Prefijo de nombre de hilo
        return scheduler;
    }
}
