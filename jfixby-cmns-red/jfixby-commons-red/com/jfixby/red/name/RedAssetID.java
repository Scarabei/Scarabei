
package com.jfixby.red.name;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;

public final class RedAssetID implements AssetID {

	public RedAssetID (final String value) {
		super();
		Debug.checkNull("AssetID String", value);
		Debug.checkEmpty("AssetID String", value);
		Debug.checkTrue("Input value contains space: <" + value + "> at " + value.indexOf(' '), value.indexOf(' ') == -1);
		// Debug.checkEmpty("value", value.replaceAll("\\\\.", ""));
		this.value = JUtils.newRelativePath(value.replaceAll("\\" + AssetID.SEPARATOR, RelativePath.SEPARATOR));
		// L.d();
		// L.d("string value", value);
		// L.d(" value", this.value);
		// L.d(" toString", this.toString());
	}

	public RedAssetID () {
		super();
		this.value = JUtils.newRelativePath();
	}

	public RedAssetID (final RelativePath value) {
		super();
		Debug.checkNull("value", value);
		this.value = value;
	}

	RelativePath value;

	@Override
	public String toString () {
		return (this.value.toString()).replaceAll(RelativePath.SEPARATOR, AssetID.SEPARATOR);
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
	public AssetID child (final String string) {
		return new RedAssetID(this.value.child(string));
	}

	@Override
	public AssetID parent () {
		return new RedAssetID(this.value.parent());
	}

	@Override
	public String getLastStep () {
		return this.value.getLastStep();
	}

	@Override
	public boolean includes (final AssetID other) {
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
	public AssetID child (final AssetID subpackage) {
		final RedAssetID red_subpackage = (RedAssetID)subpackage;
		final RelativePath new_path = this.value.proceed(red_subpackage.value);
		return new RedAssetID(new_path);
	}

}
