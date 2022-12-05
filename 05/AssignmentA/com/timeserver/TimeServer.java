package com.timeserver;

/** A Time-Server interface implementing sample-time-methods. */
public interface TimeServer {
    /** Returns the current system-clock time in milliseconds */
    public long currentTimeSystemMillis();

    /** Returns the current system-clock time formatted as a human-readable String */
    public String currentTimeGregorianPretty();
}
