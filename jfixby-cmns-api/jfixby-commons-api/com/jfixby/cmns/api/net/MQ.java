package com.jfixby.cmns.api.net;

import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.net.message.MQMessageFactory;
import com.jfixby.cmns.api.net.message.MQMessageFactorySpecs;

public class MQ {

	static private ComponentInstaller<MQComponent> componentInstaller = new ComponentInstaller<MQComponent>(
			"MQ");

	public static final void installComponent(MQComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final MQComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final MQComponent component() {
		return componentInstaller.getComponent();
	}

	public static MQMessageFactorySpecs newMessageFactorySpecs() {
		return invoke().newMessageFactorySpecs();
	}

	public static MQMessageFactory newFactory(MQMessageFactorySpecs factory_specs) {
		return invoke().newFactory(factory_specs);
	}

}
