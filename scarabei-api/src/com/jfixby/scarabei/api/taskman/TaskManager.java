
package com.jfixby.scarabei.api.taskman;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.promise.Future;
import com.jfixby.scarabei.api.promise.Promise;

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

	public static TaskManagerComponent deInstallComponent () {
		return componentInstaller.deInstallCurrentComponent();
	}

	public static Task newTask (final String debugName, final Collection<Job> jobs_sequence) {
		return invoke().newTask(debugName, jobs_sequence);
	}

	public static Task newTask (final String debugName, final Job... jobs_sequence) {
		return invoke().newTask(debugName, jobs_sequence);
	}

	public static <T> Promise<T> executeAsynchronously (final String debugName, final Future<Void, T> future) {
		return invoke().executeAsynchronously(debugName, future);
	}

	public static Promise<Void> executeAsynchronously (final String debugName) {
		return invoke().executeAsynchronously(debugName);
	}

}
