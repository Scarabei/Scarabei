
package com.jfixby.scarabei.aws.desktop.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;

class ObjectListingResult {

	private final AmazonS3Client s3;
	private final ListObjectsRequest request;
	final List<S3ObjectSummary> keyList = Collections.newList();
	final List<String> prefixList = Collections.newList();

	public ObjectListingResult (final AmazonS3Client s3, final ListObjectsRequest request) {
		this.s3 = s3;
		this.request = request;
// request.setMaxKeys(1);

		ObjectListing objects = this.s3.listObjects(request);

		this.keyList.addAll(objects.getObjectSummaries());
		this.prefixList.addAll(objects.getCommonPrefixes());

		while (objects.isTruncated()) {
			objects = this.s3.listNextBatchOfObjects(objects);
			this.keyList.addAll(objects.getObjectSummaries());
			this.prefixList.addAll(objects.getCommonPrefixes());
		}

	}

	public List<S3ObjectSummary> getObjectSummaries () {
		return this.keyList;
	}

	public List<String> getCommonPrefixes () {
		return this.prefixList;
	}

}
