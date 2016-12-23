
package com.jfixby.cmns.api.desktop;

import com.jfixby.cmns.api.angles.Angles;
import com.jfixby.cmns.api.arrays.Arrays;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.debug.Debug;
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

public class DesktopSetup {

	public static final void deploy () {
		L.installComponent("com.jfixby.red.log.SimpleLogger");
		JUtils.installComponent("com.jfixby.red.util.RedJUtils");

		Collections.installComponent("com.jfixby.red.desktop.collections.DesktopCollections");
		FloatMath.installComponent("com.jfixby.red.desktop.math.DesktopFloatMath");
		TaskManager.installComponent("com.jfixby.red.sys.RedTaskManager");
		Sys.installComponent("com.jfixby.red.desktop.sys.DesktopSystem");
		SystemSettings.installComponent("com.jfixby.red.sys.RedSystemSettings");
		IntegerMath.installComponent("com.jfixby.red.math.RedIntegerMath");
		Names.installComponent("com.jfixby.red.name.RedAssetsNamespace");
		IO.installComponent("com.jfixby.red.io.RedIO");
		Graphs.installComponent("com.jfixby.red.graphs.RedGraphs");

		Angles.installComponent("com.jfixby.red.math.RedAngles");
		Geometry.installComponent("com.jfixby.red.geometry.RedGeometry");
		Colors.installComponent("com.jfixby.red.color.RedColors");
		MathTools.installComponent("com.jfixby.red.math.RedMathTools");
		Err.installComponent("com.jfixby.red.err.RedError");
		Debug.installComponent("com.jfixby.red.debug.RedDebug");
		GCFisher.installComponent("com.jfixby.red.java.gc.RedGCFisher");
		MD5.installComponent("com.jfixby.red.util.md5.RSADataSecurityIncMD5");
		Random.installComponent("com.jfixby.red.random.RedRandom");
		Arrays.installComponent("com.jfixby.red.arrays.RedArrays");
		// --

		if (Sys.isWindows()) {
			LocalFileSystem.installComponent("com.jfixby.red.desktop.filesystem.win.WinFileSystem");
		} else if (Sys.isUnix() || Sys.isMac()) {
			LocalFileSystem.installComponent("com.jfixby.red.desktop.filesystem.unix.UnixFileSystem");
		} else {
			LocalFileSystem.installComponent("com.jfixby.red.desktop.filesystem.unix.UnixFileSystem");
		}

		Http.installComponent("com.jfixby.red.desktop.net.http.DesktopHttp");

		ImageProcessing.installComponent("com.jfixby.red.image.RedImageProcessing");
// ImageAWT.installComponent("com.jfixby.red.desktop.image.RedImageAWT");
		FileCache.installComponent("com.jfixby.red.filesystem.cache.RedFileCache");

	}

}
