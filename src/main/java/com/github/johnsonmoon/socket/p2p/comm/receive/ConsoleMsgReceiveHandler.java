package com.github.johnsonmoon.socket.p2p.comm.receive;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 16:45.
 */
public class ConsoleMsgReceiveHandler extends MsgReceiveHandler {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ConsoleMsgReceiveHandler(AtomicBoolean shutdownSwitch, Socket socket) {
        super(shutdownSwitch, socket);
    }

    @Override
    protected void lineProcess(String line) {
        String time = simpleDateFormat.format(new Date());
        System.out.println("--------------------------------");
        System.out.println("[" + time + "] receive: \"" + line + "\"");
    }
}
