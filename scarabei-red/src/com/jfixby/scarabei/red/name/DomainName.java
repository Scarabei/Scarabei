
package com.jfixby.scarabei.red.name;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public final class DomainName implements ID {

	public DomainName (final String value) {
		Debug.checkTrue("Invalid string <" + value + ">", Names.isValidString(value));
		this.value = Utils.newRelativePath(value.replaceAll("\\" + ID.SEPARATOR, RelativePath.SEPARATOR));

	}

	public DomainName () {
		super();
		this.value = Utils.newRelativePath();
	}

	public DomainName (final RelativePath value) {
		super();
		Debug.checkNull("value", value);
		this.value = value;
	}

	RelativePath value;

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		final DomainName other = (DomainName)obj;
		if (this.value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!this.value.equals(other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public ID child (final String string) {
		return new DomainName(this.value.child(string));
	}

	@Override
	public ID parent () {
		return new DomainName(this.value.parent());
	}

	@Override
	public String getLastStep () {
		return this.value.getLastStep();
	}

	@Override
	public boolean includes (final ID other) {
		Debug.checkNull("other", other);
		if (this.equals(other)) {
			return true;
		}
		final DomainName red_other = (DomainName)other;
		final boolean yes = red_other.value.beginsWith(this.value);
		// if (yes) {
		// L.d("+ " + this, other);
		// }

		return yes;

	}

	@Override
	public ID child (final ID subpackage) {
		final DomainName red_subpackage = (DomainName)subpackage;
		final RelativePath new_path = this.value.proceed(red_subpackage.value);
		return new DomainName(new_path);
	}

	@Override
	public List<String> steps () {
		return Collections.newList(this.value.steps());
	}

	@Override
	public String toString () {
		return this.toString(ID.SEPARATOR);

	}

	@Override
	public String toString (final String customSeparator) {
		return (this.value.toString()).replaceAll(RelativePath.SEPARATOR, customSeparator);
	}

}
