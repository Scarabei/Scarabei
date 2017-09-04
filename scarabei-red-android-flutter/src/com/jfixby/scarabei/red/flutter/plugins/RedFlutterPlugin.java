
package com.jfixby.scarabei.red.flutter.plugins;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugin;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class RedFlutterPlugin implements FlutterPlugin {

// private final MethodCallHandler methodCallHandler;
	ID name;
	final RedFlutterPlugins master;
	final MethodCallHandler methodCallHandler;
	ID methodCallHandlerClassName;

	public RedFlutterPlugin (final RedFlutterPlugins redFlutterPlugins, final FlutterPluginSpecs specs) {
		this.master = redFlutterPlugins;
		this.methodCallHandler = specs.methodCallHandler;

		this.methodCallHandlerClassName = null;
		if (this.methodCallHandler != null) {
			this.methodCallHandlerClassName = Names.newID(this.methodCallHandler.getClass().getCanonicalName());
		} else {
			this.methodCallHandlerClassName = specs.methodCallHandlerClassName;
			if (this.methodCallHandlerClassName == null) {
				Debug.checkNull("methodCallHandlerClassName", specs.methodCallHandlerClass);
				this.methodCallHandlerClassName = Names.newID(specs.methodCallHandlerClass.getCanonicalName());
			}
			Debug.checkNull("methodCallHandlerClassName", this.methodCallHandlerClassName);
		}

		this.name = specs.channelName;
		if (this.name == null) {
			this.name = this.methodCallHandlerClassName;
		}

		this.master.registerPlugin(this);

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
