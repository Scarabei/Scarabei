
package com.jfixby.scarabei.db.api;

public interface ConnectionParametersProvider {

	public String getHost ();

	public int getPort ();

	public String getLogin ();

	public String getPassword ();

	public String getDBName ();

}
