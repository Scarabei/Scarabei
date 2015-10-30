package com.jfixby.red.filesystem;

import com.jfixby.cmns.api.filesystem.FileHash;

public class RedFileHash implements FileHash {

	@Override
	public String toString() {
		return "FileHash [MD5=" + readable_md5 + "]";
	}

	private String md5Hex;
	private String readable_md5;

	public RedFileHash(String md5Hex) {
		this.md5Hex = md5Hex;
		String readable_md5 = "";
		for (int i = 0; i < md5Hex.length(); i = i + 2) {
			readable_md5 = readable_md5 + md5Hex.charAt(i)
					+ md5Hex.charAt(i + 1);
			if (i + 2 < md5Hex.length()) {
				readable_md5 = readable_md5 + ":";
			}
		}
		this.readable_md5 = readable_md5;
	}

	@Override
	public String getMD5HashHexString() {
		return md5Hex;
	}

	@Override
	public String getHumanReadableMD5HashHexString() {
		return readable_md5;
	}

}
