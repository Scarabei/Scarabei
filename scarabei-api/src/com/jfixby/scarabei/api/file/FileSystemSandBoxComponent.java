
package com.jfixby.scarabei.api.file;

import java.io.IOException;

public interface FileSystemSandBoxComponent {

	FileSystem wrap (String sandbox_name, File content_folder) throws IOException;

}
