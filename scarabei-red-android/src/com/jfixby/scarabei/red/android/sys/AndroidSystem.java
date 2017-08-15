
package com.jfixby.scarabei.red.android.sys;

import com.jfixby.scarabei.android.api.Android;
import com.jfixby.scarabei.android.api.AndroidSystemInfoTags;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.SystemInfoTags;
import com.jfixby.scarabei.api.ver.Version;
import com.jfixby.scarabei.red.sys.RedSystem;

public class AndroidSystem extends RedSystem {

	@Override
	public Map<ID, String> getSystemInfo () {
		final Map<ID, String> androidInfo = Android.getSystemInfo();
		androidInfo.put(Version.Tags.VersionName, androidInfo.get(AndroidSystemInfoTags.App.Version.Name));
		androidInfo.put(Version.Tags.VersionCode, androidInfo.get(AndroidSystemInfoTags.App.Version.Code));
		androidInfo.put(Version.Tags.PackageName, androidInfo.get(AndroidSystemInfoTags.App.Version.PackageName));

		androidInfo.put(SystemInfoTags.System.OS_NAME, System.getProperty("os.name"));
		androidInfo.put(SystemInfoTags.System.OS_VERSION, System.getProperty("os.version"));

		return androidInfo;
	}
}
