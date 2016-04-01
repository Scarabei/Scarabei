package com.jfixby.cmns.api.debug;

public interface DebugComponent {

    void printCallStack(boolean condition);

    void exit(boolean condition);

    void printCallStack();

    <T> T checkNull(String parameter_name, T value);

    <T> T checkNull(T value);

    String checkEmpty(String parameter_name, String value);

    void checkTrue(boolean flag);

    void checkTrue(String flag_name, boolean flag);

    DebugTimer newTimer();

    DebugTimer newTimer(DEBUG_TIMER_MODE mode);

}
