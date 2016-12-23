package com.jfixby.cmns.adopted.gdx.io;

import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.json.JsonString;

public class StringBuffer extends java.io.StringWriter {

    public JsonString toJsonString() {
	return Json.newJsonString(this.toString());
    }

}
