package com.jfixby.cmns.adopted.gdx.json;

import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.json.JsonString;

public class StringWriter extends java.io.StringWriter implements Writer {

    public JsonString toJsonString() {
	return Json.newJsonString(this.toString());
    }

}
