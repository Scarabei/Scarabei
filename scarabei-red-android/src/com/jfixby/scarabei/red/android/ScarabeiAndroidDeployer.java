
package com.jfixby.scarabei.red.android;

import com.jfixby.scarabei.android.api.AndroidComponent;
import com.jfixby.scarabei.api.log.L;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class ScarabeiAndroidDeployer {

	static boolean scarabeiGlobalDeployed = false;
	static long scarabeiDeployAttempt = -1;

	public ScarabeiAndroidDeployer () {

	}

	private boolean deploy (final Activity mainActivity) {
		scarabeiDeployAttempt++;
		if (scarabeiGlobalDeployed) {
			L.e("ScarabeiAndroid[java] scarabeiGlobalDeployed", scarabeiDeployAttempt);
			return true;
		}
		final Application app = mainActivity.getApplication();
		Log.d("starting application", "" + app);
		final AndroidComponent androidComponent = new RedAndroidComponent(app);
		ScarabeiAndroid.deploy(androidComponent);
		L.d("ScarabeiAndroid[java] scarabeiGlobalDeployed", scarabeiDeployAttempt);
		scarabeiGlobalDeployed = true;
		return true;
	}

	public void tryToDeploy (final Activity mainActivity) {
		this.deploy(mainActivity);
	}
}
