
package com.jfixby.scarabei.red.flutter.plugins;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugin;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;

import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class RedFlutterPlugin implements FlutterPlugin {

// private final MethodCallHandler methodCallHandler;
	private ID name;
	private final RedFlutterPlugins master;

	public RedFlutterPlugin (final RedFlutterPlugins redFlutterPlugins, final FlutterPluginSpecs specs) {
		this.master = redFlutterPlugins;
		final MethodCallHandler methodCallHandler = specs.methodCallHandler;

		ID methodCallHandlerClassName = null;
		if (methodCallHandler != null) {
			methodCallHandlerClassName = Names.newID(methodCallHandler.getClass().getCanonicalName());
		} else {
			methodCallHandlerClassName = specs.methodCallHandlerClassName;
			if (methodCallHandlerClassName == null) {
				Debug.checkNull("methodCallHandlerClassName", specs.methodCallHandlerClass);
				methodCallHandlerClassName = Names.newID(specs.methodCallHandlerClass.getCanonicalName());
			}
			Debug.checkNull("methodCallHandlerClassName", methodCallHandlerClassName);
		}

		this.name = specs.channelName;
		if (this.name == null) {
			this.name = methodCallHandlerClassName;
		}

		if (methodCallHandler != null) {
			this.master.registerPlugin(methodCallHandlerClassName, this.name, methodCallHandler);
		} else {
			this.master.registerPlugin(methodCallHandlerClassName, this.name, null);
		}

	}

	public static <C> C newComponent (final ID className, final ClassLoader classLoader) {
		try {
			final Class<C> klass = (Class<C>)Class.forName(className.toString(), true, classLoader);
// final Class<C> klass = (Class<C>)Class.forName(className);
			return klass.newInstance();
		} catch (final Throwable e) {
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}

	@Override
	public ID getName () {
		return this.name;
	}

// @Override
// public MethodCallHandler getHandler () {
// return this.methodCallHandler;
// }

}
