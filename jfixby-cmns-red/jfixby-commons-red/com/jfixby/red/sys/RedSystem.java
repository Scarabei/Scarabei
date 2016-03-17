package com.jfixby.red.sys;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.sys.ExecutionMode;
import com.jfixby.cmns.api.sys.Job;
import com.jfixby.cmns.api.sys.SysExecutor;
import com.jfixby.cmns.api.sys.SystemComponent;
import com.jfixby.cmns.api.sys.Task;
import com.jfixby.cmns.api.time.TimeStream;

public abstract class RedSystem implements SystemComponent {

    private final RedSystemExecutor executor = new RedSystemExecutor(this);
    private final RedSystemSettings settings = new RedSystemSettings();
    private final RedTaskManager taskman = new RedTaskManager();

    public RedSystem() {
	SysExecutor.installComponent(executor);
    }

    static final private SystemClock system_clock = new SystemClock();
    static final private NoClock no_clock = new NoClock();

    @Override
    public TimeStream SystemTime() {
	return system_clock;
    }

    @Override
    public TimeStream NoTime() {
	return no_clock;
    }

    @Override
    public Task newTask(String task_name, Job job) {
	return this.taskman.newTask(task_name, job);
    }

    @Override
    public Task newTask(String task_name, Job... jobs_sequence) {
	return this.taskman.newTask(task_name, jobs_sequence);
    }

    @Override
    public Task newTask(String task_name, Collection<Job> jobs_sequence) {
	return this.taskman.newTask(task_name, jobs_sequence);
    }

    @Override
    public Task newTask(Job job) {
	return this.taskman.newTask(job);
    }

    @Override
    public Task newTask(Job... jobs_sequence) {
	return this.taskman.newTask(jobs_sequence);
    }

    @Override
    public Task newTask(Collection<Job> jobs_sequence) {
	return this.taskman.newTask(jobs_sequence);
    }

    @Override
    public void setExecutionMode(ExecutionMode execution_mode) {
	this.settings.setExecutionMode(execution_mode);
    }

    @Override
    public ExecutionMode getExecutionMode() {
	return this.settings.getExecutionMode();
    }

    @Override
    public boolean executionModeIs(final ExecutionMode execution_mode) {
	return this.settings.executionModeIs(execution_mode);
    }

    @Override
    public void setFlag(String flag_name, boolean flag_value) {
	this.settings.setFlag(flag_name, flag_value);
    }

    @Override
    public boolean getFlag(String flag_name) {
	return this.settings.getFlag(flag_name);
    }

    @Override
    public String getStringParameter(String parameter_name) {
	return this.settings.getStringParameter(parameter_name);
    }

    @Override
    public void setStringParameter(String parameter_name, String parameter_value) {
	this.settings.setStringParameter(parameter_name, parameter_value);
    }

    @Override
    public void setSystemAssetID(String parameter_name, AssetID parameter_value) {
	this.settings.setSystemAssetID(parameter_name, parameter_value);
    }

    @Override
    public AssetID getSystemAssetID(String parameter_name) {
	return this.settings.getSystemAssetID(parameter_name);
    }

    @Override
    public void printSystemParameters() {
	this.settings.printSystemParameters();
    }

    public void push() {
	this.taskman.push();
    }

}
