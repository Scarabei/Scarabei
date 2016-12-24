
package com.jfixby.scarabei.api.sys.settings;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Mapping;

public class SystemSettings {

	static private ComponentInstaller<SystemSettingsComponent> componentInstaller = new ComponentInstaller<SystemSettingsComponent>(
		"TaskManager");

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

	public static void setFlag (final String flag_name, final boolean flag_value) {
		invoke().setFlag(flag_name, flag_value);
	}

	public static boolean getFlag (final String flag_name) {
		return invoke().getFlag(flag_name);
	}

	public static String getStringParameter (final String parameter_name) {
		return invoke().getStringParameter(parameter_name);
	}

	public static void setLongParameter (final String parameterName, final long parameterValue) {
		invoke().setLongParameter(parameterName, parameterValue);
	}

	public static void setStringParameter (final String parameter_name, final String parameter_value) {
		invoke().setStringParameter(parameter_name, parameter_value);
	}

	public static void setSystemAssetID (final String parameter_name, final ID parameter_value) {
		invoke().setSystemAssetID(parameter_name, parameter_value);
	}

	public static ID getSystemAssetID (final String parameter_name) {
		return invoke().getSystemAssetID(parameter_name);
	}

	public static void printSystemParameters () {
		invoke().printSystemParameters();
	}

	public static boolean executionModeCovers (final ExecutionMode mode) {
		return invoke().executionModeCovers(mode);
	}

	public static ExecutionMode getExecutionMode () {
		return invoke().getExecutionMode();
	}

	public static long getLongParameter (final String parameterName) {
		return invoke().getLongParameter(parameterName);
	}

	public static Mapping<String, String> listAllSettings () {
		return invoke().listAllSettings();
	}

}
