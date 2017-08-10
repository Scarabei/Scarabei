
package com.jfixby.scarabei.red.desktop;

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
import com.jfixby.scarabei.api.input.UserInput;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.java.gc.GCFisher;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.math.MathTools;
import com.jfixby.scarabei.api.md5.MD5;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.random.Random;
import com.jfixby.scarabei.api.strings.Strings;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.taskman.TaskManager;
import com.jfixby.scarabei.api.ui.UIThread;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.red.arrays.RedArrays;
import com.jfixby.scarabei.red.color.RedColors;
import com.jfixby.scarabei.red.debug.RedDebug;
import com.jfixby.scarabei.red.desktop.collections.DesktopCollections;
import com.jfixby.scarabei.red.desktop.filesystem.unix.UnixFileSystem;
import com.jfixby.scarabei.red.desktop.filesystem.win.WinFileSystem;
import com.jfixby.scarabei.red.desktop.math.DesktopFloatMath;
import com.jfixby.scarabei.red.desktop.net.http.DesktopHttp;
import com.jfixby.scarabei.red.desktop.sys.DesktopSystem;
import com.jfixby.scarabei.red.desktop.taskman.DesktopTaskManager;
import com.jfixby.scarabei.red.err.RedError;
import com.jfixby.scarabei.red.filesystem.cache.RedFileCache;
import com.jfixby.scarabei.red.geometry.RedGeometry;
import com.jfixby.scarabei.red.graphs.RedGraphs;
import com.jfixby.scarabei.red.image.RedImageProcessing;
import com.jfixby.scarabei.red.input.RedInput;
import com.jfixby.scarabei.red.io.RedIO;
import com.jfixby.scarabei.red.java.gc.RedGCFisher;
import com.jfixby.scarabei.red.json.GoogleJson;
import com.jfixby.scarabei.red.log.SimpleLogger;
import com.jfixby.scarabei.red.math.RedAngles;
import com.jfixby.scarabei.red.math.RedIntegerMath;
import com.jfixby.scarabei.red.math.RedMathTools;
import com.jfixby.scarabei.red.name.RedAssetsNamespace;
import com.jfixby.scarabei.red.random.RedRandom;
import com.jfixby.scarabei.red.string.RedStrings;
import com.jfixby.scarabei.red.sys.RedSystemSettings;
import com.jfixby.scarabei.red.ui.RedUIThread;
import com.jfixby.scarabei.red.util.RedUtils;
import com.jfixby.scarabei.red.util.md5.RSADataSecurityIncMD5;

public class ScarabeiDesktop {

	public static final void deploy () {
		L.installComponent(new SimpleLogger());
		Err.installComponent(new RedError());
		Debug.installComponent(new RedDebug());

		Utils.installComponent(new RedUtils());
		Json.installComponent(new GoogleJson());
		Collections.installComponent(new DesktopCollections());
		FloatMath.installComponent(new DesktopFloatMath());

		Sys.installComponent(new DesktopSystem());
		SystemSettings.installComponent(new RedSystemSettings());
		TaskManager.installComponent(new DesktopTaskManager());

		UIThread.installComponent(new RedUIThread());

		IntegerMath.installComponent(new RedIntegerMath());
		Names.installComponent(new RedAssetsNamespace());
		IO.installComponent(new RedIO());
		Graphs.installComponent(new RedGraphs());

		Angles.installComponent(new RedAngles());
		Geometry.installComponent(new RedGeometry());
		Colors.installComponent(new RedColors());
		MathTools.installComponent(new RedMathTools());

		GCFisher.installComponent(new RedGCFisher());
		MD5.installComponent(new RSADataSecurityIncMD5());
		Random.installComponent(new RedRandom());
		Arrays.installComponent(new RedArrays());
		Strings.installComponent(new RedStrings());
// Serialize.installComponent(new com.jfixby.scarabei.red.serialize.RedSerialize());
// --

		if (Sys.isWindows()) {
			LocalFileSystem.installComponent(new WinFileSystem());
		} else if (Sys.isUnix() || Sys.isMac()) {
			LocalFileSystem.installComponent(new UnixFileSystem());
		} else {
			LocalFileSystem.installComponent(new UnixFileSystem());
		}

		Http.installComponent(new DesktopHttp());

		ImageProcessing.installComponent(new RedImageProcessing());
// ImageAWT.installComponent(new com.jfixby.scarabei.red.desktop.image.RedImageAWT());
		FileCache.installComponent(new RedFileCache());
		UserInput.installComponent(new RedInput());
	}

}
