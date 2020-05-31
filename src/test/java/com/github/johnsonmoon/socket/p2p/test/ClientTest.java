package com.github.johnsonmoon.socket.p2p.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Create by xuyh at 2020/5/28 20:23.
 */
public class ClientTest {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 13000);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader cmdLineReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        do {
            line = cmdLineReader.readLine();
            if (line != null && !line.isEmpty()) {
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
        } while (line != null && !line.equalsIgnoreCase("exit"));
        writer.close();
        socket.close();
    }
}
