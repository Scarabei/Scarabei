package com.jfixby.cmns.adopted.gdx.json;

import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.json.JsonString;

public class StringWriter extends java.io.StringWriter implements CharWriter {

    public JsonString toJsonString() {
	return Json.newJsonString(this.toString());
    }

}
