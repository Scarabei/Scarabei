package com.jfixby.cmns.api.color;

public interface ColorSet extends ColorProjector {

    void add(Color color);

    public int size();

    public Color get(int i);

  

    void print(String tag);

}
