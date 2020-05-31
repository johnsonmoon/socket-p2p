package com.github.johnsonmoon.socket.p2p.comm.receive;

import javafx.scene.control.TextArea;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 16:47.
 */
public class UIMsgReceiveHandler extends MsgReceiveHandler {
    public UIMsgReceiveHandler(AtomicBoolean shutdownSwitch, Socket socket, TextArea textArea) {
        super(shutdownSwitch, socket);
        this.textArea = textArea;
    }

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private TextArea textArea;

    @Override
    protected void lineProcess(String line) {
        String time = simpleDateFormat.format(new Date());
        textArea.appendText("--------------------------------\r\n");
        textArea.appendText("[" + time + "] receive: \"" + line + "\"\r\n");
    }
}
