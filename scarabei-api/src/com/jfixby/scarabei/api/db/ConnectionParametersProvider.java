
package com.jfixby.scarabei.api.db;

public interface ConnectionParametersProvider {

	public String getHost ();

	public int getPort ();

	public String getLogin ();

	public String getPassword ();

	public String getDBName ();

}
