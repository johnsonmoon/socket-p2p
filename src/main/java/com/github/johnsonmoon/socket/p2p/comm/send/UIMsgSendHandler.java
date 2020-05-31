package com.github.johnsonmoon.socket.p2p.comm.send;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

/**
 * Create by xuyh at 2020/5/29 17:09.
 */
public class UIMsgSendHandler extends MsgSendHandler {
    private static Logger logger = LoggerFactory.getLogger(UIMsgSendHandler.class);

    public UIMsgSendHandler(Socket socket, TextArea textAreaMessage, TextArea textAreaSource, Button btnSend) {
        super(socket);
        this.textAreaMessage = textAreaMessage;
        this.textAreaSource = textAreaSource;
        this.btnSend = btnSend;
    }

    private TextArea textAreaMessage;
    private TextArea textAreaSource;
    private Button btnSend;

    @Override
    public void handle() {
        btnSend.setOnAction(event -> readAndWrite());

        textAreaSource.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                readAndWrite();
            }
        });

    }

    private void readAndWrite() {
        String line = textAreaSource.getText();
        if (line == null || line.isEmpty()) {
            logger.warn("Input message is null or empty.");
            textAreaMessage.appendText("Input message is null or empty.\r\n");
            return;
        }
        textAreaMessage.appendText("Send: \"" + line + "\".\r\n");
        writeLine(line);
        textAreaSource.clear();
    }
}
