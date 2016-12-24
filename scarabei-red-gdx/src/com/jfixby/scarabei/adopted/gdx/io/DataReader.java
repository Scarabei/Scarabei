package com.jfixby.scarabei.adopted.gdx.io;

public interface DataReader<DataType> {

    JsonValue parse(DataType json);

}
