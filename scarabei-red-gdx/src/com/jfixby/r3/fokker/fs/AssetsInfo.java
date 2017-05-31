
package com.jfixby.r3.fokker.fs;

import java.io.Serializable;
import java.util.Date;

import com.jfixby.scarabei.api.log.L;

public class AssetsInfo implements Serializable {

	String packed;
	String version = "0";

	public static final String FILE_NAME = "assets.info";

	public void next () {
		packed = new Date() + "";
		long current = Long.parseLong(version);
		current++;
		version = current + "";
	}

	public void print () {
		L.d("------[Assets Info]----------------------------------------------------------------------");
		L.d("          version - " + version);
		L.d("           packed - " + packed);
		// L.d("-----------------------------------------------------------------------------------------");
		L.d();
	}

	@Override
	public String toString () {
		return "AssetsInfo: {" + packed + "} version=" + version + "";
	}

}
