package com.github.johnsonmoon.socket.p2p.side.server;

import com.github.johnsonmoon.socket.p2p.comm.receive.UIMsgReceiveHandler;
import com.github.johnsonmoon.socket.p2p.comm.send.UIMsgSendHandler;
import com.github.johnsonmoon.socket.p2p.common.ThreadPools;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 17:47.
 */
public class UIServerSide extends ServerSide {
    public UIServerSide(AtomicBoolean shutdown, Integer port) {
        super(shutdown, port);
    }

    @Override
    public void receiveLoop(Socket socket, AtomicBoolean shutdownSwitch) {
        ThreadPools.msgReceiveThreadPool.submit(new UIMsgReceiveHandler(shutdownSwitch, socket));
    }

    @Override
    public void sendLoop(Socket socket) {
        new UIMsgSendHandler(socket).handle();
    }
}