package org.java_websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class ExampleClient extends WebSocketClient {

    private static Logger log = LoggerFactory.getLogger(ExampleClient.class);

    public static void main(String[] args) throws InterruptedException {
        new ExampleClient(URI.create("ws://localhost:8887"));
    }

    public ExampleClient(URI serverUri) throws InterruptedException {
        super(serverUri);

        boolean connected = this.connectBlocking();

        log.info("connected: %s".formatted(connected));
        Thread.ofPlatform().start(() -> {
            while(true) {
                try {
                    Thread.sleep(1000L);
                } catch (Exception e) {

                }
            }
        });

    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log.info("Socket open!");
    }

    @Override
    public void onMessage(String message) {
//        log.info("Message %s".formatted(message));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Code: %s, Message: %s".formatted(code, reason));
    }

    @Override
    public void onError(Exception ex) {
        log.error("Error: %s".formatted(ex.getMessage()));
    }
}
