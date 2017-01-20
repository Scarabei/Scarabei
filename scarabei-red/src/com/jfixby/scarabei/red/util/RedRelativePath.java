
package com.jfixby.scarabei.red.util;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.RelativePath;

class RedRelativePath implements RelativePath {

	final private List<String> path_steps = Collections.newList();
	private final String string;

	public RedRelativePath (final String relative_path_string) {
		Debug.checkNull("relative_path_string", relative_path_string);
		final String[] parts_array = relative_path_string.split(RelativePath.SEPARATOR);
		final List<String> parts_list = Collections.newList(parts_array);
		while (parts_list.contains(E)) {
			parts_list.remove(E);
		}
		this.path_steps.addAll(parts_list);
		this.string = toString(this.path_steps);
	}

	static private String toString (final Collection<String> path_steps) {
		final int n = path_steps.size();
		if (n == 0) {
			// return RelativePath.SEPARATOR;
			return E;
		}
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				builder.append(path_steps.getElementAt(i));
			} else {
				builder.append(RelativePath.SEPARATOR).append(path_steps.getElementAt(i));
			}
		}
		return builder.toString();
	}

	public RedRelativePath (final Collection<String> path_steps) {
		this.path_steps.addAll(path_steps);
		this.string = toString(this.path_steps);
	}

	final static String E = "";

	@Override
	public String getPathString () {
		return this.string;
	}

	@Override
	public RelativePath parent () {
		final List<String> path_steps = Collections.newList();
		path_steps.addAll(this.path_steps);
		if (this.isRoot()) {
			Err.reportError("This is already a root path. No parent available.");
		}
		path_steps.removeLast();
		return new RedRelativePath(path_steps);

	}

	@Override
	public boolean isRoot () {
		return this.path_steps.size() == 0;
	}

	public List<String> stepsList () {
		return this.path_steps;
	}

	@Override
	public RelativePath child (final String name) {
		final List<String> path_steps = Collections.newList();
		path_steps.addAll(this.path_steps);
		path_steps.add(name);
		return new RedRelativePath(path_steps);
	}

	@Override
	public String toString () {
		return this.string;
	}

	@Override
	public Collection<String> steps () {
		return this.path_steps;
	}

	@Override
	public String getLastStep () {
		return this.path_steps.getLast();
	}

	@Override
	public String getStep (final int index) {
		return this.path_steps.getElementAt(index);
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.string == null) ? 0 : this.string.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedRelativePath other = (RedRelativePath)obj;
		if (this.string == null) {
			if (other.string != null) {
				return false;
			}
		} else if (!this.string.equals(other.string)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean beginsWith (final RelativePath other) {
		Debug.checkNull("other", other);
		if (this.equals(other)) {
			return true;
		}
		return this.steps().beginsWith(other.steps());
	}

	@Override
	public RelativePath proceed (final RelativePath value) {
		Debug.checkNull(value);
		final List<String> steps = Collections.newList(this.path_steps);
		steps.addAll(value.steps());
		final RelativePath incremented = JUtils.newRelativePath(steps);
		return incremented;
	}

	@Override
	public int size () {
		return this.steps().size();
	}

	@Override
	public String nameWithoutExtension () {
		final String name = this.getLastStep();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	@Override
	public String getExtension () {
		final String name = this.getLastStep().toLowerCase();
		final int index = name.lastIndexOf('.');
		if (index < 0) {
			return null;
		}
		final String ext = name.substring(index + 1);

		return ext;
	}

	@Override
	public RelativePath splitAt (final int step) {
		return JUtils.newRelativePath(Collections.newList(this.stepsList()).splitAt(step));
	}

	@Override
	public RelativePath removeStep (final int index) {
		final List<String> newPath = this.steps().toList();
		newPath.removeElementAt(index);
		return JUtils.newRelativePath(newPath);
	}

}
