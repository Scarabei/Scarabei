
package com.jfixby.amazon.aws.s3;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.assets.NamespaceRegistry;
import com.jfixby.cmns.api.util.path.RelativePath;

public class S3FileSystemIndex {

// final Map<RelativePath, Boolean> map = Collections.newMap();
// final Map<RelativePath, S3ObjectInfo> map = Collections.newMap();
	private final NamespaceRegistry<S3ObjectInfo> registry;

	public S3FileSystemIndex () {
		this.registry = Names.newRegistry();
	}

	public static String toS3Key (final RelativePath path) {
		return null;
	}

	public Boolean isFile (final RelativePath relative) {
		return this.registry.get(this.pathToName(relative)).getLast().isFile();
	}

	private AssetID pathToName (final RelativePath relative) {
		return Names.newAssetID(relative.toString().replaceAll(RelativePath.SEPARATOR, "."));
	}

	public int size () {
		return this.registry.size();
	}

	public RelativePath getKeyAt (final int i) {
		return this.registry.getKeyAt(i);
	}

	public void print (final String string) {
		this.registry.print(string);
	}

	public S3ObjectInfo addObjectToIndex (final S3ObjectSummary objectSummary) {
		final S3ObjectInfo info = new S3ObjectInfo(objectSummary);
		this.registry.put(info.getPath(), info);
		return info;
	}

	public S3ObjectInfo getObjectInfo (final RelativePath relative) {
		return this.registry.get(relative);
	}

	public boolean containsInfoAbout (final RelativePath relative) {
		return this.registry.containsKey(relative);
	}

}
