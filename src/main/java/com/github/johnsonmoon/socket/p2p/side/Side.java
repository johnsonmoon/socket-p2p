package com.github.johnsonmoon.socket.p2p.side;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 17:19.
 */
public interface Side {
    void start();

    void receiveLoop(Socket socket, AtomicBoolean shutdownSwitch);

    void sendLoop(Socket socket);
}
