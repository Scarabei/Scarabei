package com.jfixby.cmns.api.util;

public interface BitForm {

    public static final boolean ZERO = false;
    public static final boolean ONE = !ZERO;

    void append(BitForm bitform);

    int size();

    int retrieveByte();

    BitForm append(int bits, int numberOfBits);

    boolean getBit(int i);

    void append(boolean bit);

    void insertAt(BitForm bitform, int place);

}
