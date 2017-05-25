
package com.jfixby.scarabei.aws.android.s3;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionConverter;
import com.jfixby.scarabei.api.collections.CollectionScanner;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileConflistResolver;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.InputStreamOpener;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.aws.android.s3.ObjectListingResult;
import com.jfixby.scarabei.aws.android.s3.S3File;
import com.jfixby.scarabei.aws.android.s3.S3FileInputStream;
import com.jfixby.scarabei.aws.android.s3.S3FileOutputStream;
import com.jfixby.scarabei.aws.android.s3.S3ObjectInfo;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;
import com.jfixby.scarabei.red.filesystem.AbstractFileSystem;

class AndroidS3FileSystem extends AbstractFileSystem implements FileSystem, S3FileSystem {

	private final String bucketName;
	private final AmazonS3Client s3;
	final private String toString;

	public AndroidS3FileSystem (final S3FileSystemConfig specs) {
		this.bucketName = Debug.checkNull("getBucketName()", specs.getBucketName());

		final String access_key_id = specs.getAccessKeyID();
		final String secret_key_id = specs.getSecretKeyID();
		final String regionName = specs.getRegionName();
		if (access_key_id != null && secret_key_id != null) {
			final BasicAWSCredentials awsCreds = new BasicAWSCredentials(access_key_id, secret_key_id);
			this.s3 = new AmazonS3Client(awsCreds);
		} else {
			this.s3 = new AmazonS3Client();// default credentials
		}
		if (regionName != null) {
			this.s3.setRegion(com.amazonaws.regions.Region.getRegion(com.amazonaws.regions.Regions.fromName(regionName)));
		}
		this.toString = "S3BucketFileSystem[" + this.bucketName + "]";
	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public S3File newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", this.ROOT());
			Err.reportError("Path does not belong to this filesystem: " + file_path);
		}
		return new S3File(file_path, this);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		Debug.checkNull("File", output_file);
		Debug.checkTrue("File belongs to this filesystem?", output_file.getFileSystem() == this);
		return new S3FileOutputStream((S3File)output_file, append);
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		if (input_file == null) {
			Err.reportError("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			Err.reportError("Input file does not belong to this filesystem: " + input_file);
		}

		return new S3FileInputStream((S3File)input_file);
	}

	@Override
	public String toString () {
		return this.toString;
	}

	@Override
	public boolean isReadOnlyFileSystem () {
		return false;
	}

	public String getBucketName () {
		return this.bucketName;
	}

	public AmazonS3Client getAmazonS3Client () {
		return this.s3;
	}

	static final boolean DIRECT_CHILDREN = true;
	static final boolean ALL_CHILDREN = !DIRECT_CHILDREN;

	public S3ObjectInfo listAllS3Keys (final RelativePath relative) {
		return this.retrieveInfo(relative, ALL_CHILDREN);
	}

	public S3ObjectInfo retrieveInfo (final RelativePath relative) {
		return this.retrieveInfo(relative, DIRECT_CHILDREN);
	}

	public S3ObjectInfo retrieveInfo (final RelativePath relative, final boolean directFlag) {
		if (relative.isRoot()) {
			return this.retrieveRootInfo(directFlag);
		}
		if (directFlag == DIRECT_CHILDREN) {
			final S3ObjectInfo parentInfo = this.retrieveFolderInfo(relative.parent(), directFlag);
			final boolean isFolder = parentInfo.listDirectSubfolders().contains(relative.getLastStep());
			final boolean isFile = parentInfo.listDirectChildFiles().contains(relative.getLastStep());
			final boolean exists = isFolder || isFile;

			if (!exists) {
				return null;
			}

			if (isFolder) {
				return this.retrieveFolderInfo(relative, directFlag);
			}

			return this.retrieveFileInfo(relative);
		} else {
			final S3ObjectInfo parentInfo = this.retrieveFolderInfo(relative.parent(), DIRECT_CHILDREN);
			final boolean isFolder = parentInfo.listDirectSubfolders().contains(relative.getLastStep());
			final boolean isFile = parentInfo.listDirectChildFiles().contains(relative.getLastStep());
			final boolean exists = isFolder || isFile;

			if (!exists) {
				return null;
			}

			if (isFolder) {
				return this.retrieveFolderInfo(relative, directFlag);
			}

			return this.retrieveFileInfo(relative);
		}
	}

	private S3ObjectInfo retrieveFileInfo (final RelativePath relative) {
		final ListObjectsRequest request = new ListObjectsRequest().withBucketName(this.bucketName);
		request.withPrefix(relative.toString());
		request.setDelimiter(RelativePath.SEPARATOR);
		final ObjectListingResult objectListing = this.listObjects(request);
		final S3ObjectInfo info = new S3ObjectInfo(objectListing.getObjectSummaries().getElementAt(0));
		return info;
	}

	private S3ObjectInfo retrieveFolderInfo (final RelativePath relative, final boolean directFlag) {
		if (relative.isRoot()) {
			return this.retrieveRootInfo(directFlag);
		}
		final S3ObjectInfo info;

		final ListObjectsRequest request = new ListObjectsRequest().withBucketName(this.bucketName);
		final String prefix = relative.toString() + RelativePath.SEPARATOR;

// L.d("prefix", prefix);
		request.withPrefix(prefix);
		if (directFlag == DIRECT_CHILDREN) {
			request.setDelimiter(RelativePath.SEPARATOR);

			final ObjectListingResult objectListing = this.listObjects(request);
			final List<String> prefixes = objectListing.getCommonPrefixes();
			final List<S3ObjectSummary> summs = objectListing.getObjectSummaries();
			if (summs.size() == 0) {
				L.e("  folder info not found", prefix);
				info = new S3ObjectInfo(prefix);
				info.isPresentInS3Bucket = false;
			} else {
				info = new S3ObjectInfo(summs.getElementAt(0));
			}
			final List<String> files = Collections.newList();
			Collections.scanCollection(summs, new CollectionScanner<S3ObjectSummary>() {
				@Override
				public void scanElement (final S3ObjectSummary summ, final long i) {
					final String key = summ.getKey();
					final RelativePath keyPath = JUtils.newRelativePath(key);
					if (keyPath.equals(relative)) {
						return;
					}
					if (key.endsWith(RelativePath.SEPARATOR)) {
						prefixes.add(key);
					} else {
						files.add(key);
					}
				}
			});
			info.addDirectSubfolders(prefixes);
			info.addDirectChildFiles(files);
			return info;
		} else {
			final ObjectListingResult objectListing = this.listObjects(request);
			final List<String> prefixes = objectListing.getCommonPrefixes();
			final List<S3ObjectSummary> summs = objectListing.getObjectSummaries();

			if (summs.size() == 0) {
				L.e("  folder info not found", prefix);
				info = new S3ObjectInfo(prefix);
			} else {
				info = new S3ObjectInfo(summs.getElementAt(0));
				summs.removeElementAt(0);
			}

			info.addAllChildren(summs);
// info.print("all for " + relative);
			return info;
		}
	}

	private S3ObjectInfo retrieveRootInfo (final boolean directFlag) {
		final S3ObjectInfo info = new S3ObjectInfo();
		final ListObjectsRequest request = new ListObjectsRequest().withBucketName(this.bucketName);
		request.withPrefix("");
		if (directFlag == DIRECT_CHILDREN) {
			request.setDelimiter(RelativePath.SEPARATOR);

			final ObjectListingResult objectListing = this.listObjects(request);
			final List<String> prefixes = Collections.newList(objectListing.getCommonPrefixes());
			final List<S3ObjectSummary> summs = Collections.newList(objectListing.getObjectSummaries());
			final List<String> files = Collections.newList();
			Collections.convertCollection(summs, files, new CollectionConverter<S3ObjectSummary, String>() {
				@Override
				public String convert (final S3ObjectSummary S3ObjectSummary) {
					return new S3ObjectInfo(S3ObjectSummary).path.getLastStep();
				}
			});
			info.addDirectSubfolders(prefixes);
			info.addDirectChildFiles(files);
			return info;
		} else {
			final ObjectListingResult objectListing = this.listObjects(request);
			final List<S3ObjectSummary> summs = Collections.newList(objectListing.getObjectSummaries());
			info.addAllChildren(summs);
// info.print("all for root");
			return info;

		}

	}

	private ObjectListingResult listObjects (final ListObjectsRequest request) {
		return new ObjectListingResult(this.s3, request);
	}

	boolean createS3Folder (final RelativePath relative) {
		if (relative.isRoot()) {
			return true;
		}
		RelativePath current = JUtils.newRelativePath();
		final Collection<String> steps = relative.steps();
		for (int i = 0; i < steps.size(); i++) {
			current = current.child(steps.getElementAt(i));
			this.makeFolder(current + RelativePath.SEPARATOR);
		}
		return true;
	}

	void makeFolder (final String s3Key) {
// L.d("makeFolder", s3Key);

		// create meta-data for your folder and set content-length to 0
		final ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		final ByteArrayInputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		final PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucketName, s3Key, emptyContent, metadata);
		// send request to S3 to create folder
		final PutObjectResult result = this.s3.putObject(putObjectRequest);

	}

	void writeData (final RelativePath relative, final ByteArray bytes) {

		final String s3Key = relative.toString();

		L.d("writeData", s3Key);

		// create meta-data for your folder and set content-length to 0
		final ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(bytes.size());
// create empty content
		final ByteArrayInputStream emptyContent = new ByteArrayInputStream(bytes.toArray());
		// create a PutObjectRequest passing the folder name suffixed by /
		final PutObjectRequest putObjectRequest = new PutObjectRequest(this.bucketName, s3Key, emptyContent, metadata);
		// send request to S3 to create folder
		final PutObjectResult result = this.s3.putObject(putObjectRequest);

	}

	public byte[] readData (final String s3Key) throws IOException {

		final String bucketName = this.bucketName;
		final AmazonS3Client s3 = this.s3;

		final InputStreamOpener opener = new InputStreamOpener() {
			@Override
			public java.io.InputStream open () throws IOException {
				final GetObjectRequest request = new GetObjectRequest(bucketName, s3Key);
				final S3Object object = s3.getObject(request);
				final S3ObjectInputStream is = object.getObjectContent();
				return is;
			}
		};

		final InputStream stream = IO.newInputStream(opener);
		stream.open();
		final ByteArray data = stream.readAll();
		stream.close();
		return data.toArray();
	}

	void deleteS3File (final RelativePath relative) {
		this.deleteS3Object(relative.toString());
	}

	void deleteS3Folder (final RelativePath relative) {
		this.deleteS3Object(relative.toString() + RelativePath.SEPARATOR);
	}

	void deleteS3Object (final String s3Key) {
		L.d("delete S3Key", s3Key);
		this.s3.deleteObject(this.bucketName, s3Key);
	}

	@Override
	public void copyFolderContentsToFolder (final File input_folder, final File ouput_folder, final FileConflistResolver resolver)
		throws IOException {
// super.copyFolderContentsToFolder(input_folder, ouput_folder);
		if (this == input_folder.getFileSystem()) {

			Debug.checkTrue("The folder does not exist: " + input_folder, input_folder.exists());
			Debug.checkTrue("This is not a folder: " + input_folder, input_folder.exists());
			final RelativePath start = input_folder.getAbsoluteFilePath().getRelativePath();

			final S3ObjectInfo allKeys = this.listAllS3Keys(start);
			final List<S3ObjectInfo> children = allKeys.allChildren;
			// children.print("task");
			for (int i = 0; i < children.size(); i++) {
				final S3ObjectInfo infoToCopy = children.getElementAt(i);
				final boolean isFolder = infoToCopy.s3Key.endsWith(RelativePath.SEPARATOR);
				final RelativePath file_to_copy_relative = JUtils.newRelativePath(infoToCopy.s3Key);
				final RelativePath relative = file_to_copy_relative.splitAt(start.size());
				final File target_file = ouput_folder.proceed(relative);
				final File input_file = input_folder.proceed(relative);

				if (isFolder) {
					L.d("copying", input_file);
					L.d("     to", target_file);
					target_file.makeFolder();
				} else {
					if (!target_file.exists() || resolver.overwrite(input_file, target_file)) {
						L.d("copying", input_file);
						L.d("     to", target_file);
						final byte[] bytes = this.readData(infoToCopy.s3Key);
						target_file.writeBytes(bytes);
					} else {
						L.d("   skip", input_file);
						L.d("    for", target_file);
					}
				}
			}

			return;
		}

		if (this == ouput_folder.getFileSystem()) {

			Debug.checkTrue("The folder does not exist: " + input_folder, input_folder.exists());
			Debug.checkTrue("This is not a folder: " + input_folder, input_folder.exists());
			final RelativePath start = input_folder.getAbsoluteFilePath().getRelativePath();
// final Set<File> task = Collections.newSet();
//// File pointer = ouput_folder;
// do {
// task.add(pointer);
// pointer = pointer.parent();
// } while (!pointer.getAbsoluteFilePath().isRoot());

			final FilesList task = input_folder.listAllChildren();
// task.addAll(children);
// task.print("task");
			ouput_folder.makeFolder();
			for (int i = 0; i < task.size(); i++) {
				final File file_to_copy = task.getElementAt(i);
				final RelativePath relative = file_to_copy.getAbsoluteFilePath().getRelativePath().splitAt(start.size());
				final File target = ouput_folder.proceed(relative);
				final RelativePath targetRelative = target.getAbsoluteFilePath().getRelativePath();
				// children.print("task");

				if (file_to_copy.isFolder()) {
// L.d("copying", file_to_copy);
// L.d(" to", target);
					this.makeFolder(targetRelative + RelativePath.SEPARATOR);
				} else if (file_to_copy.isFile()) {
					if (!target.exists() || resolver.overwrite(file_to_copy, target)) {
						L.d("copying", file_to_copy);
						L.d("     to", target);
						final File parent = target.parent();
						parent.makeFolder();
						this.writeData(targetRelative, file_to_copy.readBytes());
					} else {
// L.d(" skip", file_to_copy);
						L.d("                  skip", target);
					}
				}

			}

			return;
		}

		super.copyFolderContentsToFolder(input_folder, ouput_folder, resolver);

	}

}
