
package com.jfixby.scarabei.red.flutter;

import com.jfixby.scarabei.api.flutter.plugins.FlutterPlugin;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginSpecs;
import com.jfixby.scarabei.api.flutter.plugins.FlutterPluginsComponent;
import com.jfixby.scarabei.api.log.L;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class RedFlutterPlugins implements FlutterPluginsComponent {

	private final PluginRegistry registry;

	public RedFlutterPlugins (final PluginRegistry registry) {
		this.registry = registry;

	}

	void registerPlugin (final MethodChannel.MethodCallHandler plugin, final String channelName) {
		final String className = plugin.getClass().getCanonicalName();
		final Registrar registrar = this.registry.registrarFor(className);
		final MethodChannel channel = new MethodChannel(registrar.messenger(), channelName);
		channel.setMethodCallHandler(plugin);
		L.d("FlutterPlugin registered[" + channelName + "]", plugin);
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
