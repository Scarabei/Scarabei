package com.jfixby.cmns.adopted.gdx.json;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.json.JsonString;

public class GdxJsonString implements JsonString {

    final private String raw_json_string;

    public GdxJsonString(String raw_json_string) {
	Debug.checkNull("raw_json_string", raw_json_string);
	this.raw_json_string = raw_json_string;
    }

    @Override
    public char[] toCharArray() {
	return raw_json_string.toCharArray();
    }

    @Override
    public String toString() {
	return raw_json_string;
    }

}
