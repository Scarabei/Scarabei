
package com.jfixby.scarabei.aws.desktop.s3;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;

public class ContentTypeResolver {

	Map<String, String> mapping = Collections.newMap();
	static String defaultValue = "application/octet-stream";

	public ContentTypeResolver () {
		this.mapping.setDefaultValue(defaultValue);
		this.mapping.put("html", "text/html");
		this.mapping.put("css", "text/css");
		this.mapping.put("js", "application/javascript");
		this.mapping.put("png", "image/png");
		this.mapping.put("jpg", "image/jpg");
		this.mapping.put("gif", "image/gif");
	}

	public String resolve (final String extention) {
		return this.mapping.get(extention);
	}
}
