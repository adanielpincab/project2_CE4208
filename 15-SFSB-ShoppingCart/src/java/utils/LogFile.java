/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;


/**
 *
 * @author
 */

import java.util.logging.*;
import java.io.*;

public class LogFile {
    
    public static void LoginLog(String msg) throws IOException {
        Logger log = Logger.getLogger(LogFile.class.getName());

        //
        // Create an instance of FileHandler that write log to a file called
        // app.log. Each new message will be appended at the at of the log file.
        //
    
        try {
            FileHandler fileHandler = new FileHandler("login.log", true);
            log.addHandler(fileHandler);
            log.info(msg);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void JobLog(String msg) throws IOException {
        Logger log = Logger.getLogger(LogFile.class.getName());

        //
        // Create an instance of FileHandler that write log to a file called
        // app.log. Each new message will be appended at the at of the log file.
        //
    
        try {
            FileHandler fileHandler = new FileHandler("jobLog.log", true);
            log.addHandler(fileHandler);
            log.info(msg);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}