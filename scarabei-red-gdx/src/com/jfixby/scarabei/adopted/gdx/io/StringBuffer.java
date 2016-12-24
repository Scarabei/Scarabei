package com.jfixby.scarabei.adopted.gdx.io;

import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;

public class StringBuffer extends java.io.StringWriter {

    public JsonString toJsonString() {
	return Json.newJsonString(this.toString());
    }

}
