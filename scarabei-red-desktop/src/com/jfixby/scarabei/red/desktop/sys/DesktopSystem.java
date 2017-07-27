
package com.jfixby.scarabei.red.desktop.sys;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.jfixby.scarabei.api.sys.SystemInfo;
import com.jfixby.scarabei.api.sys.SystemInfoTags;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.ver.Version;
import com.jfixby.scarabei.red.sys.RedDeviceInfo;
import com.jfixby.scarabei.red.sys.RedSystem;

public class DesktopSystem extends RedSystem {

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

		{
			deviceInfo.putValue(Version.Tags.PackageName, SystemSettings.getStringParameter(Version.Tags.PackageName));
			deviceInfo.putValue(Version.Tags.VersionCode, SystemSettings.getStringParameter(Version.Tags.VersionCode));
			deviceInfo.putValue(Version.Tags.VersionName, SystemSettings.getStringParameter(Version.Tags.VersionName));
		}

		return deviceInfo;
	}

}
