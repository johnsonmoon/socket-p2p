package com.github.johnsonmoon.socket.p2p.side.client;

import com.github.johnsonmoon.socket.p2p.side.Side;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 17:17.
 */
public abstract class ClientSide implements Side {
    private static Logger logger = LoggerFactory.getLogger(ClientSide.class);
    private AtomicBoolean shutdown;
    private String host;
    private Integer port;

    public ClientSide(AtomicBoolean shutdown, String host, Integer port) {
        this.shutdown = shutdown;
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected.");
            receiveLoop(socket, shutdown);
            sendLoop(socket);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
    }
}
