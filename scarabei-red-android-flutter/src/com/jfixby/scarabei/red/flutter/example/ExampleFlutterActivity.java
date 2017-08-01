
package com.jfixby.scarabei.red.flutter.example;

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

		this.bindExamplePlugin();

	}

	private void bindExamplePlugin () {
		final FlutterPluginSpecs specs = new FlutterPluginSpecs();

		specs.methodCallHandlerClass = ExamplePlugin.class;// instance will be created automatically
		specs.channelName = null; // default name is plugin.getClass().getCanonicalName();

// specs.methodCallHandler = new ExamplePlugin();// it possible to provide a plugin instance directly

		final FlutterPlugin plugin = FlutterPlugins.registerPlugin(specs);
	}

}
