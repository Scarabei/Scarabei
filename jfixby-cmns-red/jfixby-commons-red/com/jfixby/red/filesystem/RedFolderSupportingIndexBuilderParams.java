
package com.jfixby.red.filesystem;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FolderSupportingIndexBuilderParams;

public class RedFolderSupportingIndexBuilderParams implements FolderSupportingIndexBuilderParams {

	private File home;
	private boolean rebuildRootOnly;
	private boolean ignoreHashSum;
	private boolean noOutput;
	private boolean debug;

	public RedFolderSupportingIndexBuilderParams (
		final RedFolderSupportingIndexBuilderParams redFolderSupportingIndexBuilderParams) {
		this.home = redFolderSupportingIndexBuilderParams.home;
		this.rebuildRootOnly = redFolderSupportingIndexBuilderParams.rebuildRootOnly;
		this.ignoreHashSum = redFolderSupportingIndexBuilderParams.ignoreHashSum;
		this.noOutput = redFolderSupportingIndexBuilderParams.noOutput;
		this.debug = redFolderSupportingIndexBuilderParams.debug;
	}

	public RedFolderSupportingIndexBuilderParams () {
	}

	@Override
	public void setTarget (final File home) {
		this.home = home;
	}

	@Override
	public void setRebuidOnlyForRoot (final boolean b) {
		this.rebuildRootOnly = b;
	}

	@Override
	public void setIgnoreHashSum (final boolean b) {
		this.ignoreHashSum = b;
	}

	@Override
	public void setNoOutput (final boolean b) {
		this.noOutput = b;
	}

	@Override
	public void setDebug (final boolean b) {
		this.debug = b;
	}

	@Override
	public FolderSupportingIndexBuilderParams copy () {
		return new RedFolderSupportingIndexBuilderParams(this);
	}

	@Override
	public File getTarget () {
		return this.home;
	}

	@Override
	public boolean getDebug () {
		return this.debug;
	}

	@Override
	public boolean ignoreHashSum () {
		return this.ignoreHashSum;
	}

	@Override
	public boolean rebuidOnlyForRoot () {
		return this.rebuildRootOnly;
	}

	@Override
	public boolean noOutput () {
		return this.noOutput;
	}

}
