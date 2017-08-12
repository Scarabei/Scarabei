
package com.jfixby.scarabei.red.flutter.plugins.android.sys;

public class FlutterMethodCall {
	public String methodName;
	public java.util.List<FlutterMethodCallArgument> arguments;

	public Class<?>[] listArgumetTypes () {
		final Class<?>[] result = new Class<?>[this.arguments.size()];
		int i = 0;
		for (final FlutterMethodCallArgument arg : this.arguments) {
			result[i] = arg.klass();
			i++;
		}
		return result;
	}

	public Object[] listArgumetValues () {
		final Object[] result = new Object[this.arguments.size()];
		int i = 0;
		for (final FlutterMethodCallArgument arg : this.arguments) {
			result[i] = arg.argumentValue();
			i++;
		}
		return result;
	}
}
