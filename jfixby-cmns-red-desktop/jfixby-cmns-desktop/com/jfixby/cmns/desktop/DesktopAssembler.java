package com.jfixby.cmns.desktop;

import com.jfixby.cmns.api.angles.Angles;
import com.jfixby.cmns.api.assets.Names;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.filesystem.LocalFileSystem;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.graphs.Graphs;
import com.jfixby.cmns.api.image.ImageProcessing;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.IntegerMath;
import com.jfixby.cmns.api.math.MathTools;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.jutils.desktop.DesktopUtils;
import com.jfixby.red.color.RedColors;
import com.jfixby.red.desktop.filesystem.win.WinFileSystem;
import com.jfixby.red.desktop.img.processing.DesktopImageProcessing;
import com.jfixby.red.desktop.log.DesktopLogger;
import com.jfixby.red.desktop.math.DesktopFloatMath;
import com.jfixby.red.desktop.math.RedAngles;
import com.jfixby.red.desktop.math.RedIntegerMath;
import com.jfixby.red.desktop.math.RedMathTools;
import com.jfixby.red.desktop.sys.DesktopSystem;
import com.jfixby.red.geometry.RedGeometry;
import com.jfixby.red.graphs.RedGraphs;
import com.jfixby.red.io.RedIO;
import com.jfixby.red.name.RedAssetsNamespace;

public class DesktopAssembler {

	public static final void setup() {
		L.installComponent(new DesktopLogger());
		JUtils.installComponent(new DesktopUtils());
		FloatMath.installComponent(new DesktopFloatMath());
		Sys.installComponent(new DesktopSystem());
		IntegerMath.installComponent(new RedIntegerMath());
		Names.installComponent(new RedAssetsNamespace());
		IO.installComponent(new RedIO());
		Graphs.installComponent(new RedGraphs());
		Angles.installComponent(new RedAngles());
		Geometry.installComponent(new RedGeometry());
		Colors.installComponent(new RedColors());
		MathTools.installComponent(new RedMathTools());
		// --

		LocalFileSystem.installComponent(new WinFileSystem());

		ImageProcessing.installComponent(new DesktopImageProcessing());
	}

}
