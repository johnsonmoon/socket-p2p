package com.github.johnsonmoon.socket.p2p.side.client;

import com.github.johnsonmoon.socket.p2p.comm.receive.UIMsgReceiveHandler;
import com.github.johnsonmoon.socket.p2p.comm.send.UIMsgSendHandler;
import com.github.johnsonmoon.socket.p2p.common.ThreadPools;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 17:47.
 */
public class UIClientSide extends ClientSide {
    public UIClientSide(AtomicBoolean shutdown, String host, Integer port, TextArea textArea, TextArea textAreaSource, Button btnSend) {
        super(shutdown, host, port);
        this.textArea = textArea;
        this.textAreaSource = textAreaSource;
        this.btnSend = btnSend;
    }

    private TextArea textArea;
    private TextArea textAreaSource;
    private Button btnSend;

    @Override
    public void receiveLoop(Socket socket, AtomicBoolean shutdownSwitch) {
        textArea.appendText("Connected.\r\n");
        ThreadPools.msgReceiveThreadPool.submit(new UIMsgReceiveHandler(shutdownSwitch, socket, textArea));
    }

    @Override
    public void sendLoop(Socket socket) {
        new UIMsgSendHandler(socket, textArea, textAreaSource, btnSend).handle();
    }
}
