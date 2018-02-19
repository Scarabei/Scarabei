
package com.jfixby.scarabei.api.font;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.names.ID;

public class StringSpec {

	public float fontSize = 10;
	public Color fontColor = Colors.BLACK();
	public float borderSize;
	public ID fontID;
	public Color borderColor;

	public float padding;
	public String string;

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.borderColor == null) ? 0 : this.borderColor.hashCode());
		result = prime * result + Float.floatToIntBits(this.borderSize);
		result = prime * result + ((this.fontColor == null) ? 0 : this.fontColor.hashCode());
		result = prime * result + ((this.fontID == null) ? 0 : this.fontID.hashCode());
		result = prime * result + Float.floatToIntBits(this.fontSize);
		result = prime * result + Float.floatToIntBits(this.padding);
		result = prime * result + ((this.string == null) ? 0 : this.string.hashCode());
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
		final StringSpec other = (StringSpec)obj;
		if (this.borderColor == null) {
			if (other.borderColor != null) {
				return false;
			}
		} else if (!this.borderColor.equals(other.borderColor)) {
			return false;
		}
		if (Float.floatToIntBits(this.borderSize) != Float.floatToIntBits(other.borderSize)) {
			return false;
		}
		if (this.fontColor == null) {
			if (other.fontColor != null) {
				return false;
			}
		} else if (!this.fontColor.equals(other.fontColor)) {
			return false;
		}
		if (this.fontID == null) {
			if (other.fontID != null) {
				return false;
			}
		} else if (!this.fontID.equals(other.fontID)) {
			return false;
		}
		if (Float.floatToIntBits(this.fontSize) != Float.floatToIntBits(other.fontSize)) {
			return false;
		}
		if (Float.floatToIntBits(this.padding) != Float.floatToIntBits(other.padding)) {
			return false;
		}
		if (this.string == null) {
			if (other.string != null) {
				return false;
			}
		} else if (!this.string.equals(other.string)) {
			return false;
		}
		return true;
	}

}
