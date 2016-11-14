
package com.jfixby.red.desktop;

import com.jfixby.cmns.api.angles.Angles;
import com.jfixby.cmns.api.arrays.Arrays;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.desktop.ImageAWT;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.cache.FileCache;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.graphs.Graphs;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.IntegerMath;
import com.jfixby.cmns.api.math.MathTools;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.random.Random;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.sys.settings.SystemSettings;
import com.jfixby.cmns.api.taskman.TaskManager;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.arrays.RedArrays;
import com.jfixby.red.color.RedColors;
import com.jfixby.red.debug.RedDebug;
import com.jfixby.red.desktop.collections.DesktopCollections;
import com.jfixby.red.desktop.filesystem.unix.UnixFileSystem;
import com.jfixby.red.desktop.filesystem.win.WinFileSystem;
import com.jfixby.red.desktop.image.RedImageAWT;
import com.jfixby.red.desktop.log.DesktopLogger;
import com.jfixby.red.desktop.math.DesktopFloatMath;
import com.jfixby.red.desktop.sys.DesktopSystem;
import com.jfixby.red.err.RedError;
import com.jfixby.red.filesystem.cache.RedFileCache;
import com.jfixby.red.geometry.RedGeometry;
import com.jfixby.red.graphs.RedGraphs;
import com.jfixby.red.image.RedImageProcessing;
import com.jfixby.red.io.RedIO;
import com.jfixby.red.java.gc.RedGCFisher;
import com.jfixby.red.math.RedAngles;
import com.jfixby.red.math.RedIntegerMath;
import com.jfixby.red.math.RedMathTools;
import com.jfixby.red.name.RedAssetsNamespace;
import com.jfixby.red.net.http.RedDesktopComponent;
import com.jfixby.red.random.RedRandom;
import com.jfixby.red.sys.RedSystemSettings;
import com.jfixby.red.sys.RedTaskManager;
import com.jfixby.red.util.RedJUtils;
import com.jfixby.red.util.md5.RSADataSecurityIncMD5;

public class DesktopSetup {

	public static final void deploy () {
		L.installComponent(new DesktopLogger());
		JUtils.installComponent(new RedJUtils());
		Collections.installComponent(new DesktopCollections());
		FloatMath.installComponent(new DesktopFloatMath());
		TaskManager.installComponent(new RedTaskManager());
		Sys.installComponent(new DesktopSystem());
		SystemSettings.installComponent(new RedSystemSettings());
		IntegerMath.installComponent(new RedIntegerMath());
		Names.installComponent(new RedAssetsNamespace());
		IO.installComponent(new RedIO());
		Graphs.installComponent(new RedGraphs());

		Angles.installComponent(new RedAngles());
		Geometry.installComponent(new RedGeometry());
		Colors.installComponent(new RedColors());
		MathTools.installComponent(new RedMathTools());
		Err.installComponent(new RedError());
		Debug.installComponent(new RedDebug());
		GCFisher.installComponent(new RedGCFisher());
		MD5.installComponent(new RSADataSecurityIncMD5());
		Random.installComponent(new RedRandom());
		Arrays.installComponent(new RedArrays());
		// --

		if (Sys.isWindows()) {
			LocalFileSystem.installComponent(new WinFileSystem());
		} else if (Sys.isUnix() || Sys.isMac()) {
			LocalFileSystem.installComponent(new UnixFileSystem());
		} else {
			LocalFileSystem.installComponent(new UnixFileSystem());
		}

		Http.installComponent(new RedDesktopComponent());

		ImageProcessing.installComponent(new RedImageProcessing());
		ImageAWT.installComponent(new RedImageAWT());
		FileCache.installComponent(new RedFileCache());

	}

}
