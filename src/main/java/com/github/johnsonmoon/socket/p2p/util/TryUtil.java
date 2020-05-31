package com.github.johnsonmoon.socket.p2p.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Create by xuyh at 2020/5/29 16:16.
 */
public class TryUtil {
    private static Logger logger = LoggerFactory.getLogger(TryUtil.class);

    public static <T> T tryOperation(Callable<T> operation) {
        T t = null;
        try {
            t = operation.call();
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
        return t;
    }

    public static void tryOperation(Runnable operation) {
        try {
            operation.run();
        } catch (Exception e) {
            logger.debug(e.getMessage(), e);
        }
    }
}
