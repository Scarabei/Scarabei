
package com.jfixby.scarabei.aws.api;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.aws.api.s3.S3;
import com.jfixby.scarabei.aws.api.ses.SES;
import com.jfixby.scarabei.aws.api.sns.SNS;
import com.jfixby.scarabei.aws.api.sqs.SQS;

public class AWS {

	static private ComponentInstaller<AWSComponent> componentInstaller = new ComponentInstaller<>("AWS");

	public static final void installComponent (final AWSComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static void installComponent (final String className, final ClassLoader classLoader) {
		componentInstaller.installComponent(className, classLoader);
	}

	public static final AWSComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final AWSComponent component () {
		return componentInstaller.getComponent();
	}

	public static S3 getS3 () {
		return invoke().getS3();
	}

	public static SNS getSNS () {
		return invoke().getSNS();
	}

	public static SQS getSQS () {
		return invoke().getSQS();
	}

	public static SES getSES () {
		return invoke().getSES();
	}

}
