
package com.jfixby.red.desktop.sys;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.SystemInfo;
import com.jfixby.cmns.api.sys.SystemInfoTags;
import com.jfixby.red.sys.RedDeviceInfo;
import com.jfixby.red.sys.RedSystem;

public class DesktopSystem extends RedSystem {

	@Override
	public void exit () {
		L.d("EXIT");
		System.exit(0);
	}

	@Override
	public boolean sleep (final long period) {
		try {
			Thread.sleep(period);
			return true;
		} catch (final InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SystemInfo getSystemInfo () {
		final RedDeviceInfo deviceInfo = new RedDeviceInfo();

		{
			final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			final double width = screenSize.getWidth();
			final double height = screenSize.getHeight();
			deviceInfo.putValue(SystemInfoTags.Desktop.Screen.WIDTH, width);
			deviceInfo.putValue(SystemInfoTags.Desktop.Screen.HEIGHT, height);
		}

		{
			final String osName = System.getProperty("os.name");
			deviceInfo.putValue(SystemInfoTags.System.OS_NAME, osName);
		}
		{
			final String java = System.getProperty("java.version");
			deviceInfo.putValue(SystemInfoTags.Java.Version, java);
		}

		return deviceInfo;
	}

}
