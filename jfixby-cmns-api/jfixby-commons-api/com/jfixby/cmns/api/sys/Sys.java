package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.time.TimeStream;

public class Sys {

	static private ComponentInstaller<SystemComponent> componentInstaller = new ComponentInstaller<SystemComponent>(
			"System");

	public static final void installComponent(
			SystemComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final SystemComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final SystemComponent component() {
		return componentInstaller.getComponent();
	}

	public static TimeStream SystemTime() {
		return invoke().SystemTime();
	}

	public static void exit() {
		invoke().exit();
	}

	public static boolean sleep(long period) {
		return invoke().sleep(period);
	}

	public static Task newTask(String task_name, Collection<Job> jobs_sequence) {
		return invoke().newTask(task_name, jobs_sequence);
	}

	public static Task newTask(String task_name, Job... jobs_sequence) {
		return invoke().newTask(task_name, jobs_sequence);
	}

	public static Task newTask(Collection<Job> jobs_sequence) {
		return invoke().newTask(jobs_sequence);
	}

	public static Task newTask(Job... jobs_sequence) {
		return invoke().newTask(jobs_sequence);
	}
	
	public static void setExecutionMode(ExecutionMode developer) {
		componentInstaller.invokeComponent().setExecutionMode(developer);
	}

	public static void setFlag(String flag_name, boolean flag_value) {
		componentInstaller.invokeComponent().setFlag(flag_name,
				flag_value);
	}

	public static boolean getFlag(String flag_name) {
		return componentInstaller.invokeComponent().getFlag(flag_name);
	}

	public static String getStringParameter(String parameter_name) {
		return componentInstaller.invokeComponent().getStringParameter(
				parameter_name);
	}

	public static void setStringParameter(String parameter_name,
			String parameter_value) {
		componentInstaller.invokeComponent().setStringParameter(parameter_name,
				parameter_value);
	}

	public static void setSystemAssetID(String parameter_name,
			AssetID parameter_value) {
		componentInstaller.invokeComponent().setSystemAssetID(parameter_name,
				parameter_value);
	}

	public static AssetID getSystemAssetID(String parameter_name) {
		return componentInstaller.invokeComponent().getSystemAssetID(
				parameter_name);
	}

}
