
package com.jfixby.scarabei.red.aws.test;

import java.io.IOException;
import java.util.Date;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.UploadResult;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.LocalFileSystem;

public class TestGlacier {

	public static String vaultName = "PHT";

	public static AmazonGlacierClient client;

	public static void main (final String[] args) throws IOException {

		ScarabeiDesktop.deploy();

		final com.jfixby.scarabei.api.file.File file = LocalFileSystem.ApplicationHome().child("input").child("sprite1.png");

		final ProfileCredentialsProvider credentials = new ProfileCredentialsProvider();
		client = new AmazonGlacierClient(credentials);
		client.setEndpoint("https://glacier.eu-central-1.amazonaws.com/");// https://eu-central-1.console.aws.amazon.com

		try {
			final ArchiveTransferManager atm = new ArchiveTransferManager(client, credentials);

			final UploadResult result = atm.upload(vaultName, "my archive " + (new Date()), file.toJavaFile());
			System.out.println("Archive ID: " + result.getArchiveId());

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
