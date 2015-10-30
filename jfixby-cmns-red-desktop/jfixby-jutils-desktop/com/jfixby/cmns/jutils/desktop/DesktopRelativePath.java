package com.jfixby.cmns.jutils.desktop;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.path.RelativePath;

public class DesktopRelativePath implements RelativePath {

	final private List<String> path_steps = JUtils.newList();

	public DesktopRelativePath(String relative_path_string) {
		JUtils.checkNull("relative_path_string", relative_path_string);
		String[] parts_array = relative_path_string
				.split(RelativePath.SEPARATOR);
		List<String> parts_list = JUtils.newList(parts_array);
		while (parts_list.contains(E)) {
			parts_list.remove(E);
		}
		this.path_steps.addAll(parts_list);

	}

	public DesktopRelativePath(List<String> path_steps) {
		this.path_steps.addAll(path_steps);
	}

	final static String E = "";

	@Override
	public String getPathString() {
		final int n = this.path_steps.size();
		if (n == 0) {
			// return RelativePath.SEPARATOR;
			return E;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				builder.append(path_steps.getElementAt(i));
			} else {
				builder.append(RelativePath.SEPARATOR).append(
						path_steps.getElementAt(i));
			}
		}
		return builder.toString();
	}

	@Override
	public RelativePath parent() {
		List<String> path_steps = JUtils.newList();
		path_steps.addAll(this.path_steps);
		if (this.isRoot()) {
			throw new Error("This is already a root path. No parent available.");
		}
		path_steps.removeLast();
		return new DesktopRelativePath(path_steps);

	}

	@Override
	public boolean isRoot() {
		return this.path_steps.size() == 0;
	}

	public List<String> stepsList() {
		return this.path_steps;
	}

	@Override
	public RelativePath child(String name) {
		List<String> path_steps = JUtils.newList();
		path_steps.addAll(this.path_steps);
		path_steps.add(name);
		return new DesktopRelativePath(path_steps);
	}

	@Override
	public String toString() {
		return getPathString();
	}

	@Override
	public List<String> steps() {
		return JUtils.newList(this.path_steps);
	}

	@Override
	public String getLastStep() {
		return this.path_steps.getLast();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((path_steps == null) ? 0 : path_steps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DesktopRelativePath other = (DesktopRelativePath) obj;
		if (path_steps == null) {
			if (other.path_steps != null)
				return false;
		} else {
			if (!JUtils.equalLists(this.path_steps, other.path_steps)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean beginsWith(RelativePath other) {
		JUtils.checkNull("other", other);
		if (this.equals(other)) {
			return true;
		}
		return this.steps().beginsWith(other.steps());
	}

	@Override
	public RelativePath proceed(RelativePath value) {
		JUtils.checkNull(value);
		List<String> steps = this.steps();
		steps.addAll(value.steps());
		RelativePath incremented = JUtils.newRelativePath(steps);
		return incremented;
	}

}
