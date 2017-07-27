
package com.jfixby.scarabei.red.net;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.net.http.Host;

public class RedHost implements Host {

	public final String rootString;

	public RedHost (final String rootString) {
		this.rootString = Debug.checkNull("rootString", rootString);
	}

	@Override
	public String toString () {
		return this.rootString;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.rootString == null) ? 0 : this.rootString.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedHost other = (RedHost)obj;
		if (this.rootString == null) {
			if (other.rootString != null) {
				return false;
			}
		} else if (!this.rootString.equals(other.rootString)) {
			return false;
		}
		return true;
	}

}
