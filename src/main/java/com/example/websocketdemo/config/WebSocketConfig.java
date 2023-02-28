package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();        // Phương thức registerStompEndpoints đăng ký điểm cuối “/ws”, cho phép hỗ trợ STOMP của Spring .

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");// từ client gửi mesage tới websocket phải bắt đầu bằng /app
        registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker   đường dẫn mà các client subbrice phải bắt dầu bằng /topic

        /* Tại sao chúng ta cần STOMP? Vâng, WebSocket chỉ là một giao thức truyền thông. Nó không xác định những thứ như
         - Cách gửi thư chỉ cho những người dùng đã đăng ký một chủ đề cụ thể hoặc cách gửi thư đến một người dùng cụ thể.
          Chúng tôi cần STOMP cho các chức năng này.
         */

        //   Use this for enabling a Full featured broker like RabbitMQ

        /*
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        */
    }
}
