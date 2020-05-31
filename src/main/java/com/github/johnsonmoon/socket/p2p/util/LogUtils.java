package com.github.johnsonmoon.socket.p2p.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by xuyh at 2019/4/24 11:46.
 */
public class LogUtils {
    private static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public synchronized static boolean log(String targetLogFilePathName, String appendTextContent) {
        String finalText = "-------------------------\r\n" +
                dateTimeFormat.format(new Date()) + "\r\n" +
                appendTextContent + "\r\n";

        boolean result = true;
        try {
            result = appendTextFile(targetLogFilePathName, finalText);
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return result;
    }

    public synchronized static String generateLogFileName(String dir, String name) {
        String day = dateFormat.format(new Date());
        if (name.endsWith(".log")) {
            name = name.substring(0, name.length() - 4) + "-" + day + ".log";
        } else {
            name = name + "-" + day + ".log";
        }
        return dir + "/" + name;
    }

    private static boolean appendTextFile(String filePathName, String appendTextContent) throws Exception {
        Exception exception = null;
        File file = new File(filePathName);
        if (!file.exists())
            return newTextFile(filePathName, appendTextContent);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        try {
            writer.append(appendTextContent);
            writer.flush();
        } catch (Exception e) {
            exception = e;
        } finally {
            writer.close();
        }
        if (exception != null)
            throw exception;
        return true;
    }

    private static boolean newTextFile(String filePathName, String fileTextContent) throws Exception {
        Exception exception = null;
        File file = new File(filePathName);
        if (!file.exists()) {
            file = createFileDE(filePathName);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try {
            writer.write(fileTextContent);
            writer.flush();
        } catch (Exception e) {
            exception = e;
        } finally {
            writer.close();
        }
        if (exception != null)
            throw exception;
        return true;
    }

    private static File createFileDE(String filePathName) throws Exception {
        File file = new File(filePathName);
        if (file.exists()) {
            if (!file.delete())
                return null;
        }
        if (!makeDir(file.getParentFile()))
            return null;
        if (!file.createNewFile())
            return null;
        return file;
    }

    private static boolean makeDir(File dir) {
        if (!dir.exists()) {
            File parent = dir.getParentFile();
            if (parent != null)
                makeDir(parent);
            return dir.mkdir();
        }
        return true;
    }
}
