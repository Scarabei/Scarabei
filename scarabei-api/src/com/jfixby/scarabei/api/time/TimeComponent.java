
package com.jfixby.scarabei.api.time;

public interface TimeComponent {

	ResetableTimeStream newResetableTimeStream (TimeStream systemTime);

}
