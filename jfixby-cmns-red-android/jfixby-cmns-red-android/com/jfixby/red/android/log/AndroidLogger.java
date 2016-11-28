
package com.jfixby.red.android.log;

import com.jfixby.cmns.api.log.LoggerComponent;
import com.jfixby.red.log.SimpleLogger;

import android.util.Log;

public class AndroidLogger extends SimpleLogger implements LoggerComponent {
	@Override
	public void System_err_println (final Object string) {
		Log.e("", "" + string);
	}

	@Override
	public void System_out_println (final Object string) {
		Log.d("", "" + string);
	}

	@Override
	public void System_err_println () {
		Log.e("", "");
	}

	@Override
	public void System_out_println () {
		Log.d("", "");
	}

	@Override
	public void System_out_print (final Object string) {
		System.out.print(string);
	}
}
