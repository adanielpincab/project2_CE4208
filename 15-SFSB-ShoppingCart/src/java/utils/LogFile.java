package utils;

import java.util.logging.*;
import java.io.*;

public class LogFile {
    
    public static void LoginLog(String msg) {
        /**
         * Login log
         */
        Logger log = Logger.getLogger(LogFile.class.getName());
    
        try {
            FileHandler fileHandler = new FileHandler("login.log", true);
            log.addHandler(fileHandler);
            log.info(msg);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void JobLog(String msg) throws IOException {
        /*
            Job log
        **/
        Logger log = Logger.getLogger(LogFile.class.getName());
    
        try {
            FileHandler fileHandler = new FileHandler("jobLog.log", true);
            log.addHandler(fileHandler);
            log.info(msg);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}