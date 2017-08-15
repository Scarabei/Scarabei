
package com.jfixby.scarabei.red.android;

import com.jfixby.scarabei.android.api.AndroidAppVersion;

public class RedAndroidAppVersion implements AndroidAppVersion {

	public String code;
	public String name;
	public String package_name;

	@Override
	public String getName () {
		return this.name;
	}

	@Override
	public String getCode () {
		return this.code;
	}

	@Override
	public String getPackageName () {
		return (this.package_name);
	}

}
