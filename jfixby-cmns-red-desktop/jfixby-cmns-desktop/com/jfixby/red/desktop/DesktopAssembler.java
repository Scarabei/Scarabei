
package com.jfixby.red.desktop;

import com.jfixby.cmns.api.angles.Angles;
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
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.IntegerMath;
import com.jfixby.cmns.api.math.MathTools;
import com.jfixby.cmns.api.net.http.Http;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.sys.settings.SystemSettings;
import com.jfixby.cmns.api.taskman.TaskManager;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.color.RedColors;
import com.jfixby.red.debug.RedDebug;
import com.jfixby.red.desktop.collections.DesktopCollections;
import com.jfixby.red.desktop.filesystem.win.WinFileSystem;
import com.jfixby.red.desktop.image.RedImageAWT;
import com.jfixby.red.desktop.log.DesktopLogger;
import com.jfixby.red.desktop.math.DesktopFloatMath;
import com.jfixby.red.desktop.net.HttpDesktopComponent;
import com.jfixby.red.desktop.sys.DesktopSystem;
import com.jfixby.red.err.RedError;
import com.jfixby.red.filesystem.cache.RedFileCache;
import com.jfixby.red.geometry.RedGeometry;
import com.jfixby.red.graphs.RedGraphs;
import com.jfixby.red.image.RedImageProcessing;
import com.jfixby.red.io.RedIO;
import com.jfixby.red.math.RedAngles;
import com.jfixby.red.math.RedIntegerMath;
import com.jfixby.red.math.RedMathTools;
import com.jfixby.red.name.RedAssetsNamespace;
import com.jfixby.red.sys.RedSystemSettings;
import com.jfixby.red.sys.RedTaskManager;
import com.jfixby.red.util.RedJUtils;

public class DesktopAssembler {

	public static final void setup () {
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

		// --

		LocalFileSystem.installComponent(new WinFileSystem());
		Http.installComponent(new HttpDesktopComponent());

		ImageProcessing.installComponent(new RedImageProcessing());
		ImageAWT.installComponent(new RedImageAWT());
		FileCache.installComponent(new RedFileCache());

	}

}
