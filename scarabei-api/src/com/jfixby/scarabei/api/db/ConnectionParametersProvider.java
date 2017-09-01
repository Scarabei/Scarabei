
package com.jfixby.scarabei.api.db;

import com.jfixby.scarabei.api.names.ID;

public interface ConnectionParametersProvider {

	public String getHost ();

	public int getPort ();

	public String getLogin ();

	public String getPassword ();

	public ID getDBName ();

}
