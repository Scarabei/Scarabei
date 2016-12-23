
package com.jfixby.cmns.api.net.http;

import com.jfixby.cmns.api.util.path.RelativePath;

public interface HttpURL {

	String getURLString ();

	HttpURL child (String child);

	RelativePath getRelativePath ();

}
