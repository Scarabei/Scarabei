
package com.jfixby.scarabei.red.android.sys.settings;

import com.jfixby.scarabei.android.api.Android;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettingsComponent;
import com.jfixby.scarabei.api.taskman.TaskManager;

import android.content.Context;

public class AndroidSettings implements SystemSettingsComponent {

	private static final String SHARED_PREFERENCES_NAME = "com.jfixby.scarabei.red.android.sys.settings.AndroidSettings";

	private final android.content.SharedPreferences preferences;
	private final android.content.SharedPreferences.Editor prefs;

	public AndroidSettings () {
		final Context context = Android.getApplicationContext();
		this.preferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
		this.prefs = this.preferences.edit();
	}

	@Override
	public boolean executionModeIsAtLeast (final ExecutionMode execution_mode) {
		return this.getExecutionMode().isAtLeast(execution_mode);
	}

	@Override
	public ExecutionMode getExecutionMode () {
		return ExecutionMode.resolve(this.getStringParameter(ExecutionMode.TAG, ExecutionMode.PUBLIC_RELEASE.toString()));
	}

	@Override
	public void setExecutionMode (final ExecutionMode executionMode) {
		Debug.checkNull("ExecutionMode", executionMode);
		this.prefs.putString(ExecutionMode.TAG, executionMode.toString()).apply();
		this.prefs.commit();
	}

	@Override
	public Map<String, Object> listAllSettings () {
		final Map<String, Object> params = Collections.newMap();
		final java.util.Map<String, ?> allPrefs = this.preferences.getAll();
		for (final String key : allPrefs.keySet()) {
			params.put(key, allPrefs.get(key));
		}
		return params;
	}

	@Override
	public void clearAll () {
		final java.util.Map<String, ?> allPrefs = this.preferences.getAll();
		for (final String key : allPrefs.keySet()) {
			this.prefs.remove(key);
		}
	}

	@Override
	public void setFlag (final String flag_name, final boolean flag_value) {
		this.prefs.putBoolean(flag_name, flag_value).apply();
		this.prefs.commit();
	}

	@Override
	public boolean getFlag (final String flag_name) {
		return this.preferences.getBoolean(flag_name, false);
	}

	@Override
	public String getStringParameter (final String parameter_name, final String defaultValue) {
		final String value = this.preferences.getString(parameter_name, defaultValue);
		return value;
	}

	@Override
	public ID getSystemAssetID (final String parameter_name) {
		final String value = this.preferences.getString(parameter_name, null);
		if (value == null) {
			L.e("Parameter not found", parameter_name);
			return null;
		}
		if (!Names.isValidString(value)) {
			L.e("Parameter<$parameter_name> is invalid", value);
			return null;
		}
		return Names.newID(value);
	}

	@Override
	public long getIntParameter (final String parameterName) {
		return this.preferences.getLong(parameterName, 0L);
	}

	@Override
	public void setStringParameter (final String parameter_name, final String parameter_value) {
		this.prefs.putString(parameter_name, parameter_value).apply();
		this.prefs.commit();
	}

	@Override
	public void setSystemAssetID (final String parameter_name, final ID parameter_value) {
		if (parameter_value != null) {
			this.prefs.putString(parameter_name, parameter_value.toString()).apply();
			this.prefs.commit();
		} else {
			this.prefs.putString(parameter_name, null).apply();
			this.prefs.commit();
		}
	}

	@Override
	public void setIntParameter (final String parameterName, final long parameterValue) {
		this.prefs.putLong(parameterName, parameterValue).apply();
		this.prefs.commit();
	}

	@Override
	public Promise<Boolean> saveToStorage () {
		final Future<Void, Boolean> save = new Future<Void, Boolean>() {
			@Override
			public Boolean deliver (final Void input) throws Throwable {
				final boolean result = AndroidSettings.this.prefs.commit();
				return result;
			}
		};
		return TaskManager.executeAsynchronously("SystemSettings.saveToStorage()", save);
	}

}
