
package com.jfixby.scarabei.api.desktop;

import com.jfixby.scarabei.api.angles.Angles;
import com.jfixby.scarabei.api.arrays.Arrays;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.file.cache.FileCache;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.graphs.Graphs;
import com.jfixby.scarabei.api.image.ImageProcessing;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.java.gc.GCFisher;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.math.MathTools;
import com.jfixby.scarabei.api.md5.MD5;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.random.Random;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.taskman.TaskManager;
import com.jfixby.scarabei.api.util.JUtils;

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
