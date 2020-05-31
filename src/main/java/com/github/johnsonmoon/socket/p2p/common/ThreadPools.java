package com.github.johnsonmoon.socket.p2p.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by xuyh at 2020/5/29 17:50.
 */
public class ThreadPools {
    public static ExecutorService msgReceiveThreadPool = Executors.newFixedThreadPool(100);
}
