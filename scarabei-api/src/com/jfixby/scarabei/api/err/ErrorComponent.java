
package com.jfixby.scarabei.api.err;

public interface ErrorComponent {

	void reportError (String message);

	void reportError (Throwable e);

	void reportError (String message, Throwable e);

	void reportNotImplementedYet ();

	void reportGCLeak (String msg, Object leakingObject);

	void reportError (Thread t, Throwable e);

}
