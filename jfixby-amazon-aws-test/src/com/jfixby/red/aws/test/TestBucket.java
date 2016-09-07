
package com.jfixby.red.aws.test;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jfixby.amazon.aws.s3.S3ObjectInfo;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.desktop.DesktopSetup;

public class TestBucket {

	public static void main (final String[] args) {
		DesktopSetup.deploy();
		final RelativePath target = JUtils.newRelativePath();

		final AmazonS3 s3 = new AmazonS3Client(new ProfileCredentialsProvider());
		final ListObjectsRequest request = new ListObjectsRequest().withBucketName("jfix.by");

		final String prefix = "/";
		request.withPrefix("wp-content/");
		request.setDelimiter("/");

		final ObjectListing objectListing = s3.listObjects(request);
		final List<String> prefixes = Collections.newList(objectListing.getCommonPrefixes());
		prefixes.print("prefixes");

		final List<S3ObjectSummary> summs = Collections.newList(objectListing.getObjectSummaries());
		final List<S3ObjectInfo> infos = Collections.newList();
		Collections.convertCollection(summs, infos, S3ObjectSummary -> new S3ObjectInfo(S3ObjectSummary));
//
		infos.print("all");

	}

}
