
package com.jfixby.scarabei.api.codecs;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.codecs.calls.io.CrossLanguageMethodCall;
import com.jfixby.scarabei.api.codecs.io.EncodedObject;

public class Codecs {

	static private ComponentInstaller<CodecsComponent> componentInstaller = new ComponentInstaller<CodecsComponent>("Codecs");

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final void installComponent (final CodecsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static CodecsComponent deInstallCurrentComponent () {
		return componentInstaller.deInstallCurrentComponent();
	}

	public static final CodecsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final CodecsComponent component () {
		return componentInstaller.getComponent();
	}

	public static <T> T decode (final EncodedObject encodedObject) {
		return invoke().decode(encodedObject);
	}

	public static EncodedObject encode (final Object javaObject) {
		return invoke().encode(javaObject);
	}

	public static JavaMethodCall decodeMethodCall (final CrossLanguageMethodCall flutterCall) {
		return invoke().decodeMethodCall(flutterCall);
	}

}
