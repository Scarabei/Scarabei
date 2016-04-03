package com.jfixby.cmns.adopted.gdx.json;

public interface DataReader<DataType> {

    JsonValue parse(DataType json);

}
