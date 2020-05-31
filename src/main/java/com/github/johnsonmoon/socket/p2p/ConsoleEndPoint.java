package com.github.johnsonmoon.socket.p2p;

import com.github.johnsonmoon.socket.p2p.side.client.ConsoleClientSide;
import com.github.johnsonmoon.socket.p2p.side.server.ConsoleServerSide;
import com.github.johnsonmoon.socket.p2p.util.TryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/29 16:01.
 */
public class ConsoleEndPoint {
    private static Logger logger = LoggerFactory.getLogger(ConsoleEndPoint.class);

    private static AtomicBoolean shutdownSwitch = new AtomicBoolean(false);

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Choose p2p mode. [1:client 2:server]");
            String choice = reader.readLine();
            if (choice.equalsIgnoreCase("1")) {
                //client
                System.out.println("Input connection props. [HOST:PORT]");
                String hostPort = reader.readLine();
                String[] array = hostPort.split(":");
                String host = TryUtil.tryOperation(() -> array[0]);
                Integer port = TryUtil.tryOperation(() -> Integer.parseInt(array[1]));
                new ConsoleClientSide(shutdownSwitch, host, port).start();
            } else {
                //server
                System.out.println("Input listen props. [PORT]");
                String port = reader.readLine();
                Integer portI = TryUtil.tryOperation(() -> Integer.parseInt(port));
                System.out.println("Listen port " + port + ".");
                new ConsoleServerSide(shutdownSwitch, portI).start();
            }
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
    }
}
