
package com.jfixby.cmns.api.err;

public interface ErrorComponent {

	void reportWarning (String message);

	void reportError (String message);

	void reportError (Throwable e);

	void reportError (String message, Throwable e);

	void reportNotImplementedYet ();
}
