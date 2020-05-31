package com.github.johnsonmoon.socket.p2p.side.server;

import com.github.johnsonmoon.socket.p2p.comm.receive.ConsoleMsgReceiveHandler;
import com.github.johnsonmoon.socket.p2p.comm.send.ConsoleMsgSendHandler;
import com.github.johnsonmoon.socket.p2p.common.ThreadPools;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 17:40.
 */
public class ConsoleServerSide extends ServerSide {
    public ConsoleServerSide(AtomicBoolean shutdown, Integer port) {
        super(shutdown, port);
    }

    @Override
    public void receiveLoop(Socket socket, AtomicBoolean shutdownSwitch) {
        ThreadPools.msgReceiveThreadPool.submit(new ConsoleMsgReceiveHandler(shutdownSwitch, socket));
    }

    @Override
    public void sendLoop(Socket socket) {
        new ConsoleMsgSendHandler(socket).handle();
    }
}
