package com.github.johnsonmoon.socket.p2p.comm.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Create by xuyh at 2020/5/29 17:03.
 */
public class ConsoleMsgSendHandler extends MsgSendHandler {
    private static Logger logger = LoggerFactory.getLogger(ConsoleMsgSendHandler.class);

    public ConsoleMsgSendHandler(Socket socket) {
        super(socket);
    }

    @Override
    public void handle() {
        BufferedReader cmdLineReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        do {
            try {
                line = cmdLineReader.readLine();
                if (line != null && !line.isEmpty()) {
                    if (line.equalsIgnoreCase("cls") || line.equalsIgnoreCase("clear")) {
                        System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
                        System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
                        System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
                    } else if (line.equalsIgnoreCase("exit")) {
                        System.exit(0);
                    } else {
                        writeLine(line);
                    }
                }
            } catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        } while (line != null && !line.equalsIgnoreCase("exit"));
        close();
    }
}
