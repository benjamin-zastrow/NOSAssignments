package com.timeserver;

import java.text.SimpleDateFormat;
import java.util.Date;

/** A demo implementation of the Calculator interface. */
public class TimeServerImplementation implements com.timeserver.TimeServer {
    /** Returns the current system-clock time in milliseconds */
    @Override
    public long currentTimeSystemMillis() {
        return System.currentTimeMillis();
    }

    /** Returns the current system-clock time formatted as a human-readable String */
    @Override
    public String currentTimeGregorianPretty() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd HH:mm:ss z yyyy");  
        Date date = new Date();  
        return formatter.format(date);  
    }
}
