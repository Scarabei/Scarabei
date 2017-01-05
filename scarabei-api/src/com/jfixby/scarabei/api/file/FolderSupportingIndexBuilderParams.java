
package com.jfixby.scarabei.api.file;

public interface FolderSupportingIndexBuilderParams {

	void setTarget (File home);

	void setRebuidOnlyForRoot (boolean b);

	void setIgnoreHashSum (boolean b);

	void setNoOutput (boolean b);

	void setDebug (boolean b);

	FolderSupportingIndexBuilderParams copy ();

	File getTarget ();

	boolean getDebug ();

	boolean ignoreHashSum ();

	boolean rebuidOnlyForRoot ();

	boolean noOutput ();

	void setRecoursive (boolean recoursive);

	boolean recoursive ();

	boolean ignoreJsonDecoderFailure ();

	void setIgnoreJsonDecoderFailure (boolean b);

}
