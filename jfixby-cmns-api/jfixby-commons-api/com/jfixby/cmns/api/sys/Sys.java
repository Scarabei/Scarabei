package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.components.ComponentInstaller;
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

}
