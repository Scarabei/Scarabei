
package com.jfixby.scarabei.red.desktop.sys;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.SystemInfoTags;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.ver.Version;
import com.jfixby.scarabei.red.sys.RedSystem;

public class DesktopSystem extends RedSystem {

	@Override
	public Map<ID, Object> getSystemInfo () {
		final Map<ID, Object> deviceInfo = Collections.newMap();

		{
			final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			final double width = screenSize.getWidth();
			final double height = screenSize.getHeight();
			deviceInfo.put(SystemInfoTags.Desktop.Screen.WIDTH, width + "");
			deviceInfo.put(SystemInfoTags.Desktop.Screen.HEIGHT, height + "");
		}

		{
			final String osName = System.getProperty("os.name");
			deviceInfo.put(SystemInfoTags.System.OS_NAME, osName);
		}
		{
			final String java = System.getProperty("java.version");
			deviceInfo.put(SystemInfoTags.Java.Version, java);
		}

		{
			deviceInfo.put(Version.Tags.PackageName, SystemSettings.getStringParameter(Version.Tags.PackageName, ""));
			deviceInfo.put(Version.Tags.VersionCode, SystemSettings.getStringParameter(Version.Tags.VersionCode, ""));
			deviceInfo.put(Version.Tags.VersionName, SystemSettings.getStringParameter(Version.Tags.VersionName, ""));
		}

		return deviceInfo;
	}

	@Override
	public boolean isAndroid () {
		return false;
	}

}
