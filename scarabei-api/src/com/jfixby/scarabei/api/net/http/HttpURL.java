
package com.jfixby.scarabei.api.net.http;

import com.jfixby.scarabei.api.util.path.RelativePath;

public interface HttpURL {

	String getURLString ();

	HttpURL child (String child);

	RelativePath getRelativePath ();

}
