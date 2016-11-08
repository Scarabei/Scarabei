
package com.jfixby.red.filesystem.http.descript;

import java.io.IOException;

import com.jfixby.cmns.api.collections.CollectionScanner;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.json.JsonString;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.filesystem.http.HTTPOperator;

public class DescriptorsBuilder {

	public static void rebuildDescriptors (final File file, final RebuildDescriptorsMode mode) throws IOException {
		if (!file.isFolder()) {
			Err.reportError("Is not folder " + file);
		}

		final ChildrenList children = file.listDirectChildren();
// children.print("children");
		final HttpFolderDescriptor desc = new HttpFolderDescriptor();
		Collections.scanCollection(children, new CollectionScanner<File>() {
			@Override
			public void scanElement (final File e, final int i) {
				if (e.getName().startsWith(HttpFolderDescriptor.HTTP_FOLDER_DESCRIPTOR_FILE_NAME)) {
					return;
				}
				final HttpFileEntry entry = new HttpFileEntry();
				try {
					entry.name = e.getName();

					entry.is_file = e.isFile();
					entry.is_folder = e.isFolder();
					entry.lastModified = e.lastModified();
					entry.size = e.getSize();

					entry.hash = e.calculateHash().getMD5HashHexString();

					desc.entries.put(entry.name, entry);
					if (e.isFolder() && mode == RebuildDescriptorsMode.RECURSIVELY) {

						rebuildDescriptors(e, RebuildDescriptorsMode.RECURSIVELY);

					}
				} catch (final IOException e1) {
					Err.reportError(e1);
				}

			}
		});

		HttpFolderDescriptor deckCheck = null;
		{
			final File desc_file = file.child(HttpFolderDescriptor.HTTP_FOLDER_DESCRIPTOR_FILE_NAME);
			L.d("writing", desc_file);

			final FileOutputStream os = desc_file.newOutputStream();
			os.open();
			HTTPOperator.encode(desc, os);
			os.close();

			final ByteArray dataCheck = desc_file.readBytes();
			deckCheck = HTTPOperator.decode(dataCheck);
		}
		{
			final File desc_file_json = file.child(HttpFolderDescriptor.HTTP_FOLDER_DESCRIPTOR_FILE_NAME + ".json");
			L.d("writing", desc_file_json);
			final JsonString stringData = Json.serializeToString(desc);
			final JsonString testStringData = Json.serializeToString(deckCheck);
			if (!stringData.equals(testStringData)) {
				L.d("    stringData", stringData);
				L.d("testStringData", testStringData);
				Err.reportError("decoder fails");
			}
			final String data = stringData.toString();
			desc_file_json.writeString(data);
		}
	}

}
