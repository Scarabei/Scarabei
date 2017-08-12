
package com.jfixby.scarabei.red.android;

import com.jfixby.scarabei.android.api.Android;
import com.jfixby.scarabei.android.api.AndroidComponent;
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
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.math.MathTools;
import com.jfixby.scarabei.api.md5.MD5;
import com.jfixby.scarabei.api.memory.MemoryManager;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.random.Random;
import com.jfixby.scarabei.api.strings.Strings;
import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;
import com.jfixby.scarabei.api.taskman.TaskManager;
import com.jfixby.scarabei.api.util.Utils;
import com.jfixby.scarabei.red.android.collections.AndroidCollections;
import com.jfixby.scarabei.red.android.filesystem.AndroidFileSystem;
import com.jfixby.scarabei.red.android.log.AndroidLogger;
import com.jfixby.scarabei.red.android.math.AndroidFloatMath;
import com.jfixby.scarabei.red.android.memory.AndroidMemoryManager;
import com.jfixby.scarabei.red.android.net.http.AndroidHttp;
import com.jfixby.scarabei.red.android.sys.AndroidSystem;
import com.jfixby.scarabei.red.android.sys.settings.AndroidSettings;
import com.jfixby.scarabei.red.android.taskman.AndroidTaskManager;
import com.jfixby.scarabei.red.arrays.RedArrays;
import com.jfixby.scarabei.red.color.RedColors;
import com.jfixby.scarabei.red.debug.RedDebug;
import com.jfixby.scarabei.red.err.RedError;
import com.jfixby.scarabei.red.filesystem.cache.RedFileCache;
import com.jfixby.scarabei.red.geometry.RedGeometry;
import com.jfixby.scarabei.red.graphs.RedGraphs;
import com.jfixby.scarabei.red.image.RedImageProcessing;
import com.jfixby.scarabei.red.io.RedIO;
import com.jfixby.scarabei.red.java.gc.RedGCFisher;
import com.jfixby.scarabei.red.json.GoogleJson;
import com.jfixby.scarabei.red.math.RedAngles;
import com.jfixby.scarabei.red.math.RedIntegerMath;
import com.jfixby.scarabei.red.math.RedMathTools;
import com.jfixby.scarabei.red.name.DNS;
import com.jfixby.scarabei.red.random.RedRandom;
import com.jfixby.scarabei.red.string.RedStrings;
import com.jfixby.scarabei.red.util.RedUtils;
import com.jfixby.scarabei.red.util.md5.RSADataSecurityIncMD5;

public class ScarabeiAndroid {

	public static final void deploy (final AndroidComponent android) {
		Utils.installComponent(new RedUtils());
		Names.installComponent(new DNS());
		L.installComponent(new AndroidLogger());
		Err.installComponent(new RedError());
		Debug.installComponent(new RedDebug());
		Android.installComponent(android);
		SystemSettings.installComponent(new AndroidSettings());

		Sys.installComponent(new AndroidSystem());

		Strings.installComponent(new RedStrings());
		Collections.installComponent(new AndroidCollections());
		FloatMath.installComponent(new AndroidFloatMath());
		TaskManager.installComponent(new AndroidTaskManager());

		IntegerMath.installComponent(new RedIntegerMath());

		IO.installComponent(new RedIO());
		Graphs.installComponent(new RedGraphs());
		Json.installComponent(new GoogleJson());
		Angles.installComponent(new RedAngles());
		Geometry.installComponent(new RedGeometry());
		Colors.installComponent(new RedColors());
		MathTools.installComponent(new RedMathTools());

		MemoryManager.installComponent(new AndroidMemoryManager());
		L.d("Max heap size: ", MemoryManager.getMaxHeapSize() + "Mb");
		L.d("Recommended heap size: ", MemoryManager.getRecommendedHeapSize() + "Mb");

		GCFisher.installComponent(new RedGCFisher());

		MD5.installComponent(new RSADataSecurityIncMD5());
		Random.installComponent(new RedRandom());
		Arrays.installComponent(new RedArrays());

		{
			LocalFileSystem.installComponent(new AndroidFileSystem());
		}

		Http.installComponent(new AndroidHttp());

		ImageProcessing.installComponent(new RedImageProcessing());
		FileCache.installComponent(new RedFileCache());

	}

	public static final void main (final String[] arg) {

	}

}
