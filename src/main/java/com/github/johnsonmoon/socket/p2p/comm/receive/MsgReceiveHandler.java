package com.github.johnsonmoon.socket.p2p.comm.receive;

import com.github.johnsonmoon.socket.p2p.util.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 16:37.
 */
public abstract class MsgReceiveHandler implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(MsgReceiveHandler.class);

    private AtomicBoolean shutdownSwitch;
    private Socket socket;

    protected MsgReceiveHandler(AtomicBoolean shutdownSwitch, Socket socket) {
        this.shutdownSwitch = shutdownSwitch;
        this.socket = socket;
    }

    protected abstract void lineProcess(String line);

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (!shutdownSwitch.get()) {
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    LogUtils.log(
                            LogUtils.generateLogFileName(System.getProperty("user.dir") + "/chat-logs", "chat-log"),
                            "Receive: " + line
                    );
                    lineProcess(line);
                }
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
