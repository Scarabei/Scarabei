
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
		return (value.toString()).replaceAll(RelativePath.SEPARATOR, AssetID.SEPARATOR);
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		RedAssetID other = (RedAssetID)obj;
		if (value == null) {
			if (other.value != null) return false;
		} else if (!value.equals(other.value)) return false;
		return true;
	}

	@Override
	public AssetID child (String string) {
		return new RedAssetID(value.child(string));
	}

	@Override
	public AssetID parent () {
		return new RedAssetID(value.parent());
	}

	@Override
	public String getLastStep () {
		return this.value.getLastStep();
	}

	@Override
	public boolean includes (AssetID other) {
		Debug.checkNull("other", other);
		if (this.equals(other)) {
			return true;
		}
		RedAssetID red_other = (RedAssetID)other;
		final boolean yes = red_other.value.beginsWith(this.value);
		// if (yes) {
		// L.d("+ " + this, other);
		// }

		return yes;

	}

	@Override
	public AssetID child (AssetID subpackage) {
		RedAssetID red_subpackage = (RedAssetID)subpackage;
		RelativePath new_path = this.value.proceed(red_subpackage.value);
		return new RedAssetID(new_path);
	}
}
