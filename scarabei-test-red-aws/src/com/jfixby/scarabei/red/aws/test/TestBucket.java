
package com.jfixby.scarabei.red.aws.test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class TestBucket {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();
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
