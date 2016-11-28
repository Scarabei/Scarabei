
package com.jfixby.cmns.ver;

import java.io.Serializable;

import com.jfixby.cmns.api.debug.Debug;

public class Version implements Serializable {

	public static class Tags {
		public static final String VersionName = "app.version.name";
		public static final String VersionCode = "app.version.code";
		public static final String PackageName = "app.version.package_name";
	}

	public static final String VERSION_FILE_NAME = "version.json";

	private static final long serialVersionUID = 6662721574596241247L;

	public String packageName;

	public int major = -1;
	public int minor = -1;
	public VERSION_STAGE stage = null;
	public int build = -1;
	public int versionCode = -1;

	public String getVersionString () {
		Debug.checkNull("packageName", this.packageName);
		Debug.checkEmpty("packageName", this.packageName);
		Debug.checkNull("stage", this.stage);
		Debug.checkTrue("major is invalid: " + this.major, this.major >= 0);
		Debug.checkTrue("minor is invalid: " + this.minor, this.minor >= 0);
		Debug.checkTrue("build is invalid: " + this.build, this.build >= 0);
		return this.major + "." + this.minor + "." + this.stage.tagName + "." + this.build;
	}

	public String getPackageVersionString () {
		return this.packageName + "-" + this.getVersionString();
	}

}
