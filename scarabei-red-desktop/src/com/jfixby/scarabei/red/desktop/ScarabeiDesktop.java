
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
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.red.json.GoogleJson;

public class ScarabeiDesktop {

	public static final void deploy () {
		L.installComponent(new com.jfixby.scarabei.red.log.SimpleLogger());
		Utils.installComponent(new com.jfixby.scarabei.red.util.RedUtils());
		Json.installComponent(new GoogleJson());
		Collections.installComponent(new com.jfixby.scarabei.red.desktop.collections.DesktopCollections());
		FloatMath.installComponent(new com.jfixby.scarabei.red.desktop.math.DesktopFloatMath());
		TaskManager.installComponent(new com.jfixby.scarabei.red.sys.RedTaskManager());
		Sys.installComponent(new com.jfixby.scarabei.red.desktop.sys.DesktopSystem());
		SystemSettings.installComponent(new com.jfixby.scarabei.red.sys.RedSystemSettings());
		IntegerMath.installComponent(new com.jfixby.scarabei.red.math.RedIntegerMath());
		Names.installComponent(new com.jfixby.scarabei.red.name.RedAssetsNamespace());
		IO.installComponent(new com.jfixby.scarabei.red.io.RedIO());
		Graphs.installComponent(new com.jfixby.scarabei.red.graphs.RedGraphs());

		Angles.installComponent(new com.jfixby.scarabei.red.math.RedAngles());
		Geometry.installComponent(new com.jfixby.scarabei.red.geometry.RedGeometry());
		Colors.installComponent(new com.jfixby.scarabei.red.color.RedColors());
		MathTools.installComponent(new com.jfixby.scarabei.red.math.RedMathTools());
		Err.installComponent(new com.jfixby.scarabei.red.err.RedError());
		Debug.installComponent(new com.jfixby.scarabei.red.debug.RedDebug());
		GCFisher.installComponent(new com.jfixby.scarabei.red.java.gc.RedGCFisher());
		MD5.installComponent(new com.jfixby.scarabei.red.util.md5.RSADataSecurityIncMD5());
		Random.installComponent(new com.jfixby.scarabei.red.random.RedRandom());
		Arrays.installComponent(new com.jfixby.scarabei.red.arrays.RedArrays());
		Strings.installComponent(new com.jfixby.scarabei.red.string.RedStrings());
// Serialize.installComponent(new com.jfixby.scarabei.red.serialize.RedSerialize());
// --

		if (Sys.isWindows()) {
			LocalFileSystem.installComponent(new com.jfixby.scarabei.red.desktop.filesystem.win.WinFileSystem());
		} else if (Sys.isUnix() || Sys.isMac()) {
			LocalFileSystem.installComponent(new com.jfixby.scarabei.red.desktop.filesystem.unix.UnixFileSystem());
		} else {
			LocalFileSystem.installComponent(new com.jfixby.scarabei.red.desktop.filesystem.unix.UnixFileSystem());
		}

		Http.installComponent(new com.jfixby.scarabei.red.desktop.net.http.DesktopHttp());

		ImageProcessing.installComponent(new com.jfixby.scarabei.red.image.RedImageProcessing());
// ImageAWT.installComponent(new com.jfixby.scarabei.red.desktop.image.RedImageAWT());
		FileCache.installComponent(new com.jfixby.scarabei.red.filesystem.cache.RedFileCache());
		UserInput.installComponent(new com.jfixby.scarabei.red.input.RedInput());
	}

}
