package com.github.johnsonmoon.socket.p2p.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Create by xuyh at 2020/5/28 20:23.
 */
public class ServerTest {
    private static AtomicBoolean shutdown = new AtomicBoolean(false);
    private static ExecutorService service = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(13000, 100);
        while (!shutdown.get()) {
            service.submit(new SocketHandler(serverSocket.accept(), shutdown));
        }
    }


    public static class SocketHandler implements Runnable {
        private Socket socket;
        private AtomicBoolean shutdown;

        public SocketHandler(Socket socket, AtomicBoolean shutdown) {
            this.socket = socket;
            this.shutdown = shutdown;
        }

        @Override
        public void run() {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (!shutdown.get()) {
                    String line = reader.readLine();
                    System.out.println("Receive line: " + line);
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
}
