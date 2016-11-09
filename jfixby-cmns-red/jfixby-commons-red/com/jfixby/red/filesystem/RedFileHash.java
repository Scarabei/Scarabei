
package com.jfixby.red.filesystem;

import com.jfixby.cmns.api.file.FileHash;

public final class RedFileHash implements FileHash {

	@Override
	public String toString () {
		return "FileHash [MD5=" + this.readable_md5 + "]";
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
		final RedFileHash other = (RedFileHash)obj;
		if (this.md5Hex == null) {
			if (other.md5Hex != null) {
				return false;
			}
		} else if (!this.md5Hex.equals(other.md5Hex)) {
			return false;
		}
		return true;
	}

	public RedFileHash (final String md5Hex) {
		this.md5Hex = md5Hex.toLowerCase();
		String readable_md5 = "";
		for (int i = 0; i < md5Hex.length(); i = i + 2) {
			readable_md5 = readable_md5 + md5Hex.charAt(i) + md5Hex.charAt(i + 1);
			if (i + 2 < md5Hex.length()) {
				readable_md5 = readable_md5 + ":";
			}
		}
		this.readable_md5 = readable_md5;
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
