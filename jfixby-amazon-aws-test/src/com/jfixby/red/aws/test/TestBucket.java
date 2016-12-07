
package com.jfixby.red.aws.test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;

public class TestBucket {

	public static void main (final String[] args) {
		DesktopSetup.deploy();
		final RelativePath target = JUtils.newRelativePath();

		final AmazonS3 s3 = new AmazonS3Client(new ProfileCredentialsProvider());
		final ListObjectsRequest request = new ListObjectsRequest().withBucketName("jfix.by");

		final String prefix = "/";
		request.withPrefix("wp-content");
		request.setDelimiter("");

		final ObjectListing objectListing = s3.listObjects(request);
		final List<String> prefixes = Collections.newList(objectListing.getCommonPrefixes());
		prefixes.print("prefixes");

		final List<S3ObjectSummary> summs = Collections.newList(objectListing.getObjectSummaries());

	}

}
