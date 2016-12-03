
package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.time.TimeStream;

public class Sys {

	static private ComponentInstaller<SystemComponent> componentInstaller = new ComponentInstaller<SystemComponent>("System");

	public static final void installComponent (final SystemComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final SystemComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final SystemComponent component () {
		return componentInstaller.getComponent();
	}

	public static TimeStream SystemTime () {
		return invoke().SystemTime();
	}

	public static TimeStream NoTime () {
		return invoke().NoTime();
	}

	public static void exit () {
		invoke().exit();
	}

	public static boolean sleep (final long period) {
		return invoke().sleep(period);
	}

	public static boolean isWindows () {
		return invoke().isWindows();
	}

	public static boolean isUnix () {
		return invoke().isUnix();
	}

	public static boolean isMac () {
		return invoke().isMac();
	}

	public static SystemInfo getSystemInfo () {
		return invoke().getSystemInfo();
	}

	public static void yeld () {
		invoke().yeld();
	}

	public static void wait (final Object lock) {
		invoke().wait(lock);
	}

}
