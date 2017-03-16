
package com.jfixby.scarabei.examples.json;

import java.util.Vector;

public class ExempleObjectClass {

	public static class ChildA {
		public String A = "a";
		Vector<String> v1 = new Vector<>();

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
			final ChildA other = (ChildA)obj;
			if (this.A == null) {
				if (other.A != null) {
					return false;
				}
			} else if (!this.A.equals(other.A)) {
				return false;
			}
			if (this.v1 == null) {
				if (other.v1 != null) {
					return false;
				}
			} else if (!this.v1.equals(other.v1)) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode () {
			final int prime = 31;
			int result = 1;
			result = (prime * result) + ((this.A == null) ? 0 : this.A.hashCode());
			result = (prime * result) + ((this.v1 == null) ? 0 : this.v1.hashCode());
			return result;
		}

	}

	public static class ChildB {
		public String A = "a";

		public String B = "b";

		Vector<String> v2 = new Vector<>();

		public ChildB () {
		}

		public ChildB (final String string) {
			this.v2.addElement(string);
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
			final ChildB other = (ChildB)obj;
			if (this.A == null) {
				if (other.A != null) {
					return false;
				}
			} else if (!this.A.equals(other.A)) {
				return false;
			}
			if (this.B == null) {
				if (other.B != null) {
					return false;
				}
			} else if (!this.B.equals(other.B)) {
				return false;
			}
			if (this.v2 == null) {
				if (other.v2 != null) {
					return false;
				}
			} else if (!this.v2.equals(other.v2)) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode () {
			final int prime = 31;
			int result = 1;
			result = (prime * result) + ((this.A == null) ? 0 : this.A.hashCode());
			result = (prime * result) + ((this.B == null) ? 0 : this.B.hashCode());
			result = (prime * result) + ((this.v2 == null) ? 0 : this.v2.hashCode());
			return result;
		}

	}

	public static class ChildC {
		public String A = "a";
		public String B = "b";
		public String C = "c";
		Vector<ChildB> v3 = new Vector<>();

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
			final ChildC other = (ChildC)obj;
			if (this.A == null) {
				if (other.A != null) {
					return false;
				}
			} else if (!this.A.equals(other.A)) {
				return false;
			}
			if (this.B == null) {
				if (other.B != null) {
					return false;
				}
			} else if (!this.B.equals(other.B)) {
				return false;
			}
			if (this.C == null) {
				if (other.C != null) {
					return false;
				}
			} else if (!this.C.equals(other.C)) {
				return false;
			}
			if (this.v3 == null) {
				if (other.v3 != null) {
					return false;
				}
			} else if (!this.v3.equals(other.v3)) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode () {
			final int prime = 31;
			int result = 1;
			result = (prime * result) + ((this.A == null) ? 0 : this.A.hashCode());
			result = (prime * result) + ((this.B == null) ? 0 : this.B.hashCode());
			result = (prime * result) + ((this.C == null) ? 0 : this.C.hashCode());
			result = (prime * result) + ((this.v3 == null) ? 0 : this.v3.hashCode());
			return result;
		}

	}

	public String A = "a";
	public String B = "b";

	public String C = "c";

	ChildA Ca = new ChildA();

	ChildB Cb = new ChildB();

	ChildC Cc = new ChildC();

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
		final ExempleObjectClass other = (ExempleObjectClass)obj;
		if (this.A == null) {
			if (other.A != null) {
				return false;
			}
		} else if (!this.A.equals(other.A)) {
			return false;
		}
		if (this.B == null) {
			if (other.B != null) {
				return false;
			}
		} else if (!this.B.equals(other.B)) {
			return false;
		}
		if (this.C == null) {
			if (other.C != null) {
				return false;
			}
		} else if (!this.C.equals(other.C)) {
			return false;
		}
		if (this.Ca == null) {
			if (other.Ca != null) {
				return false;
			}
		} else if (!this.Ca.equals(other.Ca)) {
			return false;
		}
		if (this.Cb == null) {
			if (other.Cb != null) {
				return false;
			}
		} else if (!this.Cb.equals(other.Cb)) {
			return false;
		}
		if (this.Cc == null) {
			if (other.Cc != null) {
				return false;
			}
		} else if (!this.Cc.equals(other.Cc)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.A == null) ? 0 : this.A.hashCode());
		result = (prime * result) + ((this.B == null) ? 0 : this.B.hashCode());
		result = (prime * result) + ((this.C == null) ? 0 : this.C.hashCode());
		result = (prime * result) + ((this.Ca == null) ? 0 : this.Ca.hashCode());
		result = (prime * result) + ((this.Cb == null) ? 0 : this.Cb.hashCode());
		result = (prime * result) + ((this.Cc == null) ? 0 : this.Cc.hashCode());
		return result;
	}
}
