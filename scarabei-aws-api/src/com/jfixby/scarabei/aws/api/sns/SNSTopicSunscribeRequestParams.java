
package com.jfixby.scarabei.aws.api.sns;

public interface SNSTopicSunscribeRequestParams {

	void setRegion (String string);

	String getRegion ();

	void setTopicARN (String string);

	String getTopicARN ();

	void setProtocol (String string);

	String getProtocol ();

	public String getEndPoint ();

	public String setEndPoint (String endPoint);;

}
