package com.jfixby.cmns.api.net;

import com.jfixby.cmns.api.net.message.MQMessageFactory;
import com.jfixby.cmns.api.net.message.MQMessageFactorySpecs;

public interface MQComponent {

	public MQMessageFactorySpecs newMessageFactorySpecs();

	public MQMessageFactory newFactory(MQMessageFactorySpecs factorySpecs);

}
