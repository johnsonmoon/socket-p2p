package com.github.johnsonmoon.socket.p2p.comm.receive;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 16:47.
 */
public class UIMsgReceiveHandler extends MsgReceiveHandler {
    public UIMsgReceiveHandler(AtomicBoolean shutdownSwitch, Socket socket) {
        super(shutdownSwitch, socket);
    }

    @Override
    protected void lineProcess(String line) {
        //TODO
    }
}
