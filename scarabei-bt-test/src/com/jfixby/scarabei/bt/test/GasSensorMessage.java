
package com.jfixby.scarabei.bt.test;

import com.jfixby.scarabei.api.log.L;

public class GasSensorMessage {

	public long globalCounter;
	public byte[] data;

	public void print () {
		L.d("---GasSensorMessage[" + this.globalCounter + "]-----------");
		L.d("data", this.data);
		L.d();
	}

}
