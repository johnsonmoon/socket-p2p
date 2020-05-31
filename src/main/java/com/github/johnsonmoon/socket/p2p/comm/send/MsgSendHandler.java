package com.github.johnsonmoon.socket.p2p.comm.send;

import com.github.johnsonmoon.socket.p2p.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Create by xuyh at 2020/5/29 16:49.
 */
public abstract class MsgSendHandler {
    private static Logger logger = LoggerFactory.getLogger(MsgSendHandler.class);

    private Socket socket;
    private BufferedWriter writer;

    public MsgSendHandler(Socket socket) {
        this.socket = socket;
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
    }

    protected void writeLine(String line) {
        try {
            LogUtils.log(
                    LogUtils.generateLogFileName(System.getProperty("user.dir") + "/chat-logs", "chat-log"),
                    "Send: " + line
            );
            writer.write(line);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
    }

    protected void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }
    }

    public abstract void handle();
}
