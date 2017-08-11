
package com.jfixby.scarabei.red.name;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public final class RedAssetID implements ID {

	public RedAssetID (final String value) {
		Debug.checkTrue("Invalid string <" + value + ">", Names.isValidString(value));
		this.value = Utils.newRelativePath(value.replaceAll("\\" + ID.SEPARATOR, RelativePath.SEPARATOR));

	}

	public RedAssetID () {
		super();
		this.value = Utils.newRelativePath();
	}

	public RedAssetID (final RelativePath value) {
		super();
		Debug.checkNull("value", value);
		this.value = value;
	}

	RelativePath value;

	@Override
	public String toString () {
		return (this.value.toString()).replaceAll(RelativePath.SEPARATOR, ID.SEPARATOR);
	}

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
		final RedAssetID other = (RedAssetID)obj;
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
		return new RedAssetID(this.value.child(string));
	}

	@Override
	public ID parent () {
		return new RedAssetID(this.value.parent());
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
		final RedAssetID red_other = (RedAssetID)other;
		final boolean yes = red_other.value.beginsWith(this.value);
		// if (yes) {
		// L.d("+ " + this, other);
		// }

		return yes;

	}

	@Override
	public ID child (final ID subpackage) {
		final RedAssetID red_subpackage = (RedAssetID)subpackage;
		final RelativePath new_path = this.value.proceed(red_subpackage.value);
		return new RedAssetID(new_path);
	}

	@Override
	public List<String> steps () {
		return Collections.newList(this.value.steps());
	}

}
