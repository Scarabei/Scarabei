
package com.jfixby.scarabei.api;

public class ScarabeiAPIVersion {

	public static final ScarabeiAPIVersion VERSION = new ScarabeiAPIVersion("Luxembourg-F", "#170315",
		"https://github.com/Scarabei/Scarabei");

	private final String name;
	private final String build_id;
	private final String homepage;

	@Override
	public String toString () {
		return "ScarabeiAPIVersion [name=" + this.name + ", build_id=" + this.build_id + ", homepage=" + this.homepage + "]";
	}

	public ScarabeiAPIVersion (final String name, final String buildID, final String homepage) {
		this.name = name;
		this.build_id = buildID;
		this.homepage = homepage;
	}

	public String getName () {
		return this.name;
	}

	public String getBuildID () {
		return this.build_id;
	}

	public String getHomePage () {
		return this.homepage;
	}

}
