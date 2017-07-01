
package com.jfixby.r3.fokker.fs;

import com.jfixby.scarabei.api.file.File;

public class GdxAssetsFileSystemPackerParams {

	public File inputAssetsFolder;
	public File outputAssetsFolder;
	public boolean collapseFolders = false;
	public boolean encryptNames = false;
	public String encryptionSalt = "";
	public boolean justBuildIndex = false;

}
