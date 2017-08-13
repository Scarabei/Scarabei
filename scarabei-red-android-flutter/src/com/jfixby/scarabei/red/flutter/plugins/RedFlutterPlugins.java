
package com.jfixby.scarabei.red.flutter.plugins;

import java.lang.reflect.Method;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugin;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginsComponent;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class RedFlutterPlugins implements FlutterPluginsComponent {

	private final PluginRegistry registry;

	public RedFlutterPlugins (final PluginRegistry registry) {
		this.registry = registry;
	}

	void registerPlugin (final ID methodCallHandlerClassName, final ID channelName, MethodCallHandler instance) {
		final ID className = methodCallHandlerClassName;
		final Registrar registrar = this.registry.registrarFor(className.toString());

		final ClassLoader classLoader = this.getClass().getClassLoader();
		Class<MethodCallHandler> klass;
		try {
			klass = (Class<MethodCallHandler>)Class.forName(methodCallHandlerClassName.toString(), true, classLoader);
		} catch (final ClassNotFoundException e) {
			klass = null;
			L.e("FlutterPlugin failed[" + channelName + "]");
			Err.reportError(e);
		}

		if (instance == null) {
			try {
				instance = klass.newInstance();
			} catch (final InstantiationException | IllegalAccessException e) {
				L.e("failed to create instance of the ", klass);
			}
		}

		if (instance != null) {
			final MethodChannel channel = new MethodChannel(registrar.messenger(), channelName.toString());
			channel.setMethodCallHandler(instance);
		} else {

			final String methodname = "registerWith";
			Method registerWith;
			try {
				registerWith = klass.getMethod(methodname, Registrar.class);
			} catch (final NoSuchMethodException e) {
				L.e("FlutterPlugin failed[" + channelName + "]");
				Err.reportError(e);
				registerWith = null;
			}

			try {
				registerWith.invoke(null, registrar);
// /*
// * public static void registerWith(Registrar registrar) {
// * MethodChannel channel = new MethodChannel(registrar.messenger(), "plugins.flutter.io/url_launcher");
// * UrlLauncherPlugin instance = new UrlLauncherPlugin(registrar.activity());
// * channel.setMethodCallHandler(instance);
// * }
// */
			} catch (final Exception e) {
				L.e("FlutterPlugin failed[" + channelName + "]");
				Err.reportError(e);
			}

		}

		L.d("FlutterPlugin registered[" + channelName + "]");

	}

	@Override
	public FlutterPluginSpecs newPluginSpecs () {
		return new FlutterPluginSpecs();
	}

	@Override
	public FlutterPlugin registerPlugin (final FlutterPluginSpecs specs) {
		return new RedFlutterPlugin(this, specs);
	}

}
