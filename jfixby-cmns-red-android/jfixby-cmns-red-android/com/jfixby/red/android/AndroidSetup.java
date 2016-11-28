
package com.jfixby.red.android;

import com.jfixby.android.api.Android;
import com.jfixby.android.api.AndroidComponent;
import com.jfixby.cmns.adopted.gdx.GdxSimpleTriangulator;
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
import com.jfixby.cmns.api.math.SimpleTriangulator;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.memory.MemoryManager;
import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.random.Random;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.sys.settings.SystemSettings;
import com.jfixby.cmns.api.taskman.TaskManager;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.android.collections.AndroidCollections;
import com.jfixby.red.android.filesystem.AndroidFileSystem;
import com.jfixby.red.android.log.AndroidLogger;
import com.jfixby.red.android.math.AndroidFloatMath;
import com.jfixby.red.android.memory.AndroidMemoryManager;
import com.jfixby.red.android.net.http.AndroidHttp;
import com.jfixby.red.android.sys.AndroidSystem;
import com.jfixby.red.arrays.RedArrays;
import com.jfixby.red.color.RedColors;
import com.jfixby.red.debug.RedDebug;
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
import com.jfixby.red.random.RedRandom;
import com.jfixby.red.sys.RedSystemSettings;
import com.jfixby.red.sys.RedTaskManager;
import com.jfixby.red.util.RedJUtils;
import com.jfixby.red.util.md5.RSADataSecurityIncMD5;

public class AndroidSetup {

	public static final void deploy (final AndroidComponent android) {
		L.installComponent(new AndroidLogger());
		JUtils.installComponent(new RedJUtils());
		Collections.installComponent(new AndroidCollections());
		FloatMath.installComponent(new AndroidFloatMath());
		TaskManager.installComponent(new RedTaskManager());
		Sys.installComponent(new AndroidSystem());
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

		Android.installComponent(android);
		MemoryManager.installComponent(new AndroidMemoryManager());
		L.d("Max heap size: ", MemoryManager.getMaxHeapSize() + "Mb");
		L.d("Recommended heap size: ", MemoryManager.getRecommendedHeapSize() + "Mb");

		GCFisher.installComponent(new RedGCFisher());

		MD5.installComponent(new RSADataSecurityIncMD5());
		Random.installComponent(new RedRandom());
		Arrays.installComponent(new RedArrays());

		SimpleTriangulator.installComponent(new GdxSimpleTriangulator());

		{
			LocalFileSystem.installComponent(new AndroidFileSystem());
		}

		Http.installComponent(new AndroidHttp());

		ImageProcessing.installComponent(new RedImageProcessing());
		FileCache.installComponent(new RedFileCache());

	}

}
