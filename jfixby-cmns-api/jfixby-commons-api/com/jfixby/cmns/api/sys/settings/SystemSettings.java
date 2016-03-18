package com.jfixby.cmns.api.sys.settings;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.assets.AssetID;

public class SystemSettings {

    static private ComponentInstaller<SystemSettingsComponent> componentInstaller = new ComponentInstaller<SystemSettingsComponent>(
	    "TaskManager");

    public static final void installComponent(SystemSettingsComponent component_to_install) {
	componentInstaller.installComponent(component_to_install);
    }

    public static final SystemSettingsComponent invoke() {
	return componentInstaller.invokeComponent();
    }

    public static final SystemSettingsComponent component() {
	return componentInstaller.getComponent();
    }

    public static void setExecutionMode(ExecutionMode developer) {
	componentInstaller.invokeComponent().setExecutionMode(developer);
    }

    public static void setFlag(String flag_name, boolean flag_value) {
	componentInstaller.invokeComponent().setFlag(flag_name, flag_value);
    }

    public static boolean getFlag(String flag_name) {
	return componentInstaller.invokeComponent().getFlag(flag_name);
    }

    public static String getStringParameter(String parameter_name) {
	return componentInstaller.invokeComponent().getStringParameter(parameter_name);
    }

    public static void setStringParameter(String parameter_name, String parameter_value) {
	componentInstaller.invokeComponent().setStringParameter(parameter_name, parameter_value);
    }

    public static void setSystemAssetID(String parameter_name, AssetID parameter_value) {
	componentInstaller.invokeComponent().setSystemAssetID(parameter_name, parameter_value);
    }

    public static AssetID getSystemAssetID(String parameter_name) {
	return componentInstaller.invokeComponent().getSystemAssetID(parameter_name);
    }

    public static void printSystemParameters() {
	invoke().printSystemParameters();
    }

    public static boolean executionModeIs(ExecutionMode mode) {
	return invoke().executionModeIs(mode);
    }

    public static ExecutionMode getExecutionMode() {
	return invoke().getExecutionMode();
    }

}
