
package com.jfixby.scarabei.red.flutter.example;

import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugin;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugins;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginsComponent;
import com.jfixby.scarabei.red.android.ScarabeiAndroidDeployer;
import com.jfixby.scarabei.red.flutter.RedFlutterPlugins;

import android.os.Bundle;
import io.flutter.app.FlutterActivity;

public class ExampleFlutterActivity extends FlutterActivity {

	final ScarabeiAndroidDeployer deployer = new ScarabeiAndroidDeployer();

	@Override
	protected void onCreate (final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.deployer.tryToDeploy(this);

		final FlutterPluginsComponent flutterPlugins = new RedFlutterPlugins(this);
		FlutterPlugins.installComponent(flutterPlugins);

		final FlutterPluginSpecs specs = FlutterPlugins.newPluginSpecs();

		specs.methodCallHandler = new ExamplePlugin();
		specs.channelName = null;
		specs.methodCallHandlerClassName = Names.newID(ExamplePlugin.class.getCanonicalName());

		final FlutterPlugin plugin = FlutterPlugins.registerPlugin(specs);

	}

}
