
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

public class ScarabeiDesktop {

	public static final void deploy () {
		L.installComponent("com.jfixby.scarabei.red.log.SimpleLogger");
		JUtils.installComponent("com.jfixby.scarabei.red.util.RedJUtils");

		Collections.installComponent("com.jfixby.scarabei.red.desktop.collections.DesktopCollections");
		FloatMath.installComponent("com.jfixby.scarabei.red.desktop.math.DesktopFloatMath");
		TaskManager.installComponent("com.jfixby.scarabei.red.sys.RedTaskManager");
		Sys.installComponent("com.jfixby.scarabei.red.desktop.sys.DesktopSystem");
		SystemSettings.installComponent("com.jfixby.scarabei.red.sys.RedSystemSettings");
		IntegerMath.installComponent("com.jfixby.scarabei.red.math.RedIntegerMath");
		Names.installComponent("com.jfixby.scarabei.red.name.RedAssetsNamespace");
		IO.installComponent("com.jfixby.scarabei.red.io.RedIO");
		Graphs.installComponent("com.jfixby.scarabei.red.graphs.RedGraphs");

		Angles.installComponent("com.jfixby.scarabei.red.math.RedAngles");
		Geometry.installComponent("com.jfixby.scarabei.red.geometry.RedGeometry");
		Colors.installComponent("com.jfixby.scarabei.red.color.RedColors");
		MathTools.installComponent("com.jfixby.scarabei.red.math.RedMathTools");
		Err.installComponent("com.jfixby.scarabei.red.err.RedError");
		Debug.installComponent("com.jfixby.scarabei.red.debug.RedDebug");
		GCFisher.installComponent("com.jfixby.scarabei.red.java.gc.RedGCFisher");
		MD5.installComponent("com.jfixby.scarabei.red.util.md5.RSADataSecurityIncMD5");
		Random.installComponent("com.jfixby.scarabei.red.random.RedRandom");
		Arrays.installComponent("com.jfixby.scarabei.red.arrays.RedArrays");
// Serialize.installComponent("com.jfixby.scarabei.red.serialize.RedSerialize");
// --

		if (Sys.isWindows()) {
			LocalFileSystem.installComponent("com.jfixby.scarabei.red.desktop.filesystem.win.WinFileSystem");
		} else if (Sys.isUnix() || Sys.isMac()) {
			LocalFileSystem.installComponent("com.jfixby.scarabei.red.desktop.filesystem.unix.UnixFileSystem");
		} else {
			LocalFileSystem.installComponent("com.jfixby.scarabei.red.desktop.filesystem.unix.UnixFileSystem");
		}

		Http.installComponent("com.jfixby.scarabei.red.desktop.net.http.DesktopHttp");

		ImageProcessing.installComponent("com.jfixby.scarabei.red.image.RedImageProcessing");
// ImageAWT.installComponent("com.jfixby.scarabei.red.desktop.image.RedImageAWT");
		FileCache.installComponent("com.jfixby.scarabei.red.filesystem.cache.RedFileCache");

	}

}
