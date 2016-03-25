package com.jfixby.cmns.api.util;

public interface BinaryCode {

    public static final boolean ZERO = false;
    public static final boolean ONE = !ZERO;

    void append(BinaryCode bitform);

    int size();

    int retrieveByte();

    BinaryCode append(int bits, int numberOfBits);

    boolean getBit(int i);

    void append(boolean bit);

    void insertAt(BinaryCode bitform, int place);

}
