package com.github.johnsonmoon.socket.p2p.side.server;

import com.github.johnsonmoon.socket.p2p.side.Side;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 17:17.
 */
public abstract class ServerSide implements Side {
    private static Logger logger = LoggerFactory.getLogger(ServerSide.class);
    private AtomicBoolean shutdown;
    private Integer port;

    public ServerSide(AtomicBoolean shutdown, Integer port) {
        this.shutdown = shutdown;
        this.port = port;
    }

    @Override
    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port, 100);
            while (!shutdown.get()) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected.");
                receiveLoop(socket, shutdown);
                sendLoop(socket);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
    }
}
