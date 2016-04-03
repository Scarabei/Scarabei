package com.jfixby.cmns.adopted.gdx.io;

public interface DataReader<DataType> {

    JsonValue parse(DataType json);

}
