
package com.jfixby.scarabei.aws.api;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public class AWSCredentialsViaSystemSettingsProvider implements AWSCredentialsProvider {

	public static String readParam (final ID parameter_name) {
		final String value = SystemSettings.getStringParameter(parameter_name, null);
		return Debug.checkNull("" + parameter_name, value);
	}

	@Override
	public String getAccessKeyID () {
		return readParam(AWSCredentials.AWS_ACCESS_KEY());
	}

	@Override
	public String getSecretKeyID () {
		return readParam(AWSCredentials.AWS_SECRET_KEY());
	}

	@Override
	public String getRegionName () {
		return readParam(AWSCredentials.AWS_REGION_NAME());
	}
}
