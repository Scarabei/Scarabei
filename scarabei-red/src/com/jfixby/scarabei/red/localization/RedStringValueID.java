
package com.jfixby.scarabei.red.localization;

import com.jfixby.scarabei.api.localize.StringValueID;

public class RedStringValueID implements StringValueID {

	private String string_value_id_string;

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string_value_id_string == null) ? 0 : string_value_id_string.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		RedStringValueID other = (RedStringValueID)obj;
		if (string_value_id_string == null) {
			if (other.string_value_id_string != null) return false;
		} else if (!string_value_id_string.equals(other.string_value_id_string)) return false;
		return true;
	}

	@Override
	public String toString () {
		return "" + string_value_id_string + "";
	}

	public RedStringValueID (String string_value_id_string) {
		this.string_value_id_string = string_value_id_string;
	}
}
