
package com.jfixby.scarabei.red.util.md5;

import com.jfixby.scarabei.api.file.FileHash;
import com.jfixby.scarabei.api.md5.MD5String;

public class RedMD5String implements MD5String, FileHash {

	public RedMD5String (final String string) {
		this.md5Hex = string.toLowerCase();
		String readable_md5 = "";
		for (int i = 0; i < this.md5Hex.length(); i = i + 2) {
			readable_md5 = readable_md5 + this.md5Hex.charAt(i) + this.md5Hex.charAt(i + 1);
			if (i + 2 < this.md5Hex.length()) {
				readable_md5 = readable_md5 + ":";
			}
		}
		this.readable_md5 = readable_md5;
	}

	@Override
	public String toString () {
		return this.md5Hex;
	}

	private final String md5Hex;
	private final String readable_md5;

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.md5Hex == null) ? 0 : this.md5Hex.hashCode());
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
		final RedMD5String other = (RedMD5String)obj;
		if (this.md5Hex == null) {
			if (other.md5Hex != null) {
				return false;
			}
		} else if (!this.md5Hex.equals(other.md5Hex)) {
			return false;
		}
		return true;
	}

	@Override
	public String getMD5HashHexString () {
		return this.md5Hex;
	}

	@Override
	public String getHumanReadableMD5HashHexString () {
		return this.readable_md5;
	}

}
