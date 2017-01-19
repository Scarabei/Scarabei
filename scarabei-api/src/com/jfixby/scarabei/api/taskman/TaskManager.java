
package com.jfixby.scarabei.api.taskman;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.Collection;

public class TaskManager {

	static private ComponentInstaller<TaskManagerComponent> componentInstaller = new ComponentInstaller<TaskManagerComponent>(
		"TaskManager");

	public static final void installComponent (final TaskManagerComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final TaskManagerComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final TaskManagerComponent component () {
		return componentInstaller.getComponent();
	}

	public static Task newTask (final String task_name, final Collection<Job> jobs_sequence) {
		return invoke().newTask(task_name, jobs_sequence);
	}

	public static Task newTask (final String task_name, final Job... jobs_sequence) {
		return invoke().newTask(task_name, jobs_sequence);
	}

	public static Task newTask (final Collection<Job> jobs_sequence) {
		return invoke().newTask(jobs_sequence);
	}

	public static Task newTask (final Job... jobs_sequence) {
		return invoke().newTask(jobs_sequence);
	}

	public static TaskSpecs newTaskSpecs () {
		return invoke().newTaskSpecs();
	}

	public static Task newTask (final TaskSpecs specs) {
		return invoke().newTask(specs);
	}

	public static SimpleProgress newSimpleProgress () {
		return invoke().newSimpleProgress();
	}

	public static boolean executeImmediately (final Job job) {
		return invoke().executeImmediately(job);
	}

}
