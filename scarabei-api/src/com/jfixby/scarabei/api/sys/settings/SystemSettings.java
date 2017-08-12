
package com.jfixby.scarabei.api.sys.settings;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.promise.Promise;

public class SystemSettings {

	static private ComponentInstaller<SystemSettingsComponent> componentInstaller = new ComponentInstaller<SystemSettingsComponent>(
		"SystemSettings");

	public static final void installComponent (final SystemSettingsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final SystemSettingsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final SystemSettingsComponent component () {
		return componentInstaller.getComponent();
	}

	public static void setExecutionMode (final ExecutionMode developer) {
		componentInstaller.invokeComponent().setExecutionMode(developer);
	}

	public static void setFlag (final ID flag_name, final boolean flag_value) {
		invoke().setFlag(flag_name, flag_value);
	}

	public static boolean getFlag (final ID flag_name) {
		return invoke().getFlag(flag_name);
	}

	public static String getStringParameter (final ID parameter_name, final String defaultValue) {
		return invoke().getStringParameter(parameter_name, defaultValue);
	}

	public static void setStringParameter (final ID parameter_name, final String parameter_value) {
		invoke().setStringParameter(parameter_name, parameter_value);
	}

	public static void setSystemAssetID (final ID parameter_name, final ID parameter_value) {
		invoke().setSystemAssetID(parameter_name, parameter_value);
	}

	public static ID getSystemAssetID (final ID parameter_name) {
		return invoke().getSystemAssetID(parameter_name);
	}

	public static boolean executionModeCovers (final ExecutionMode mode) {
		return invoke().executionModeIsAtLeast(mode);
	}

	public static ExecutionMode getExecutionMode () {
		return invoke().getExecutionMode();
	}

	public static long getLongParameter (final ID parameterName) {
		return invoke().getIntParameter(parameterName);
	}

	public static Map<ID, Object> listAllSettings () {
		return invoke().listAllSettings();
	}

	public static void clearAll () {
		invoke().clearAll();
	}

	public static void setIntParameter (final ID key, final long longValue) {
		invoke().setIntParameter(key, longValue);
	}

	public static Promise<Boolean> saveToStorage () {
		return invoke().saveToStorage();
	}

}
