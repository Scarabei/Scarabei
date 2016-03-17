package com.jfixby.cmns.api.debug;

public interface DebugTimer {

    void reset();

    long getTime();

    void printTime(String tag);

    void printTimeAbove(long threshold, String tag);

}
