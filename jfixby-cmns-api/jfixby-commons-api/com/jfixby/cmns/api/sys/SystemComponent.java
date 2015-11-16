package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.time.TimeStream;

public interface SystemComponent {

	TimeStream SystemTime();

	void exit();

	boolean sleep(long period);

	Task newTask(String task_name, Job job);

	Task newTask(String task_name, Job... jobs_sequence);

	Task newTask(String task_name, Collection<Job> jobs_sequence);

	Task newTask(Job job);

	Task newTask(Job... jobs_sequence);

	Task newTask(Collection<Job> jobs_sequence);

	// Task newSerialTask(AnyCollectionType<Task> tasks);

	// /-------------------------------------------

	void setExecutionMode(ExecutionMode execution_mode);

	void setFlag(String flag_name, boolean flag_value);

	boolean getFlag(String flag_name);

	String getStringParameter(String parameter_name);

	void setStringParameter(String parameter_name, String parameter_value);

	void setSystemAssetID(String parameter_name, AssetID parameter_value);

	AssetID getSystemAssetID(String parameter_name);

}
