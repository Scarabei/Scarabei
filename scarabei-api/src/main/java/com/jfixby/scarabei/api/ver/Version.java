
package com.jfixby.scarabei.api.ver;

import java.io.Serializable;

import com.jfixby.scarabei.api.debug.Debug;

public class Version implements Serializable {

	public static class Tags {
		public static final String VersionName = "app.version.name";
		public static final String VersionCode = "app.version.code";
		public static final String PackageName = "app.version.package_name";
	}

	public static final String VERSION_FILE_NAME = "version.json";

	private static final long serialVersionUID = 6662721574596241247L;

	public String packageName;

	public String major = "";
	public String minor = "";
	public String build = "";
	public int versionCode = -1;

	public String getVersionString () {
		Debug.checkNull("packageName", this.packageName);
		Debug.checkEmpty("packageName", this.packageName);
		Debug.checkEmpty("major is invalid", this.major);
		Debug.checkEmpty("minor is invalid", this.minor);
		Debug.checkEmpty("build is invalid ", this.build);
		Debug.checkNull("major is invalid", this.major);
		Debug.checkNull("minor is invalid", this.minor);
		Debug.checkNull("build is invalid ", this.build);
		return this.major + "." + this.minor + "." + this.build;
	}

	public String getPackageVersionString () {
		return this.packageName + "-" + this.getVersionString() + " (versionCode: " + this.versionCode + ")";
	}

}
