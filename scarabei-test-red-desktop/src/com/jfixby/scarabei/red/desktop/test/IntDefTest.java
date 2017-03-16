
package com.jfixby.scarabei.red.desktop.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class IntDefTest {

// @IntDef({VISIBLE, INVISIBLE, GONE})
	@Retention(RetentionPolicy.SOURCE)
	public @interface Visibility {
	}

	public static final int VISIBLE = 4;
	public static final int INVISIBLE = 3;
	public static final int GONE = 2;

}
