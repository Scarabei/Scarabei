
package com.jfixby.scarabei.red.filesystem;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.CollectionScanner;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.FolderSupportingIndex;
import com.jfixby.scarabei.api.file.FolderSupportingIndexBuilderParams;
import com.jfixby.scarabei.api.file.FolderSupportingIndexEntry;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.net.http.HTTPOperator;

public class RedFolderSupportingIndexBuilder {

	static public FolderSupportingIndex rebuild (final FolderSupportingIndexBuilderParams params) throws IOException {
		final File file = params.getTarget();

		if (!file.isFolder()) {
			Err.reportError("Is not folder " + file);
		}
		if (params.getDebug()) {
			L.d(file);
		}
		final FilesList children = file.listDirectChildren();
// children.print("children");
		final FolderSupportingIndex desc = new FolderSupportingIndex();
		Collections.scanCollection(children, new CollectionScanner<File>() {
			@Override
			public void scanElement (final File e, final long i) {
				if (e.getName().startsWith(FolderSupportingIndex.FILE_NAME)) {
					return;
				}
				final FolderSupportingIndexEntry entry = new FolderSupportingIndexEntry();
				try {
					entry.name = e.getName();

					entry.is_file = e.isFile();
					entry.is_folder = e.isFolder();
					entry.lastModified = e.lastModified();
					entry.size = e.getSize();
					if (!params.ignoreHashSum() && e.isFile()) {
						entry.hash = e.calculateHash().getMD5HashHexString();
					}
					desc.entries.put(entry.name, entry);
					if (e.isFolder()) {
						final FolderSupportingIndexBuilderParams paramsNext = params.copy();
						paramsNext.setTarget(e);
						if (params.rebuidOnlyForRoot()) {
							paramsNext.setNoOutput(true);
						} else {
							paramsNext.setNoOutput(params.noOutput());
// paramsNext.noOutput = params.noOutput;
						}
						paramsNext.setIgnoreHashSum(params.ignoreHashSum());
						paramsNext.setDebug(params.getDebug());

						if (params.recoursive()) {
							final FolderSupportingIndex sublevel = rebuild(paramsNext);
							desc.children.put(entry.name, sublevel);
						}
					}
				} catch (final IOException e1) {
					Err.reportError(e1);
				}

			}
		});
		if (!params.noOutput()) {
			FolderSupportingIndex deckCheck = null;
			{
				final File desc_file = file.child(FolderSupportingIndex.FILE_NAME);
				L.d("writing", desc_file);

				final FileOutputStream os = desc_file.newOutputStream();
				os.open();
				HTTPOperator.encode(desc, os);
				os.close();

				final ByteArray dataCheck = desc_file.readBytes();
				deckCheck = HTTPOperator.decode(dataCheck);
			}
			{
				final File desc_file_json = file.child(FolderSupportingIndex.FILE_NAME + ".json");
				L.d("writing", desc_file_json);
				final JsonString stringData = Json.serializeToString(desc);
				final JsonString testStringData = Json.serializeToString(deckCheck);
				final String s1 = stringData.toString();
				final String s2 = testStringData.toString();
				if (!s1.equals(s2)) {
					final String message = "decoder fails";
					L.e("    stringData " + s1.length());
					L.e("testStringData " + s2.length());
					L.e(desc_file_json);
// for (int k = 0; k < s1.length(); k++) {
// final char c1 = s1.charAt(k);
// final char c2 = s2.charAt(k);
// if (c1 != c2) {
//// L.d_appendChars("#");L.d_appendChars("#");L.d_appendChars("#");
//// L.d_appendChars("" + c1);
//// L.d_appendChars("#");
// } else {
//// L.d_appendChars("" + c1);
// }
// }

					if (params.ignoreJsonDecoderFailure()) {
						L.e(message);
					} else {
						Err.reportError(message);
					}
				}
				final String data = stringData.toString();
				desc_file_json.writeString(data);
			}
		}
		return desc;
	}

}
