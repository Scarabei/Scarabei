
package com.jfixby.scarabei.api.promise;

public interface PromiseProcessor<I, O> {

	O process (I promised);

}
