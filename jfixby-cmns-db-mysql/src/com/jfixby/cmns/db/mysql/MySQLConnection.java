
package com.jfixby.cmns.db.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;

public class MySQLConnection {

	final String serverName;
	final String login;
	final String password;
	final String dbName;

	final boolean useSSL;
	public final int connectionDrainTime;
	MySQLConnectionDrainer connectionDrainer = null;

	public final Object lock = new Object();

	private final StateSwitcher<CONNECTON_STATE> state;

	public MySQLConnection (final String serverName, final String login, final String password, final String dbName,
		final boolean useSSL, final int connectionDrainTime) {
		this.serverName = Debug.checkNull("serverName", serverName);
		this.login = Debug.checkNull("login", login);
		this.password = Debug.checkNull("password", password);
		this.dbName = Debug.checkNull("dbName", dbName);
		this.useSSL = useSSL;
		this.connectionDrainTime = connectionDrainTime;
		this.state = JUtils.newStateSwitcher(CONNECTON_STATE.CLOSED);
	}

	public boolean open () {
		synchronized (this.lock) {
			this.state.expectState(CONNECTON_STATE.CLOSED);
			this.state.switchState(CONNECTON_STATE.OPEN);
			Debug.checkTrue(this.connectionDrainer == null);
			return true;
		}
	}

	public Connection getConnection () throws SQLException {
		synchronized (this.lock) {
			if (this.state.stateIs(CONNECTON_STATE.CLOSED)) {
				Err.reportError("connection is closed");
				return null;
			}

			if (this.state.stateIs(CONNECTON_STATE.LIVE)) {
				this.connectionDrainer.updateCloseTime();
				return this.connectionDrainer.getSQLConnection();
			}

			this.state.expectState(CONNECTON_STATE.OPEN);
			Debug.checkTrue(this.connectionDrainer == null);
			this.connectionDrainer = new MySQLConnectionDrainer(this);
			try {
				this.connectionDrainer.connect();
			} catch (final SQLException e) {
				this.connectionDrainer.dispose();
				this.connectionDrainer = null;
// this.state.switchState(CONNECTON_STATE.CLOSED);
				throw e;
			}
			this.connectionDrainer.start();
			this.state.switchState(CONNECTON_STATE.LIVE);
			return this.connectionDrainer.getSQLConnection();
		}
	}

	public void close () {
		synchronized (this.lock) {
			if (this.state.stateIs(CONNECTON_STATE.CLOSED)) {
				Debug.checkTrue(this.connectionDrainer == null);
				return;
			}
			if (this.state.stateIs(CONNECTON_STATE.OPEN)) {
				Debug.checkTrue(this.connectionDrainer == null);
				this.state.switchState(CONNECTON_STATE.CLOSED);
				return;
			}
			if (this.state.stateIs(CONNECTON_STATE.LIVE)) {
				Debug.checkNull(this.connectionDrainer);
				this.connectionDrainer.retire();
				this.connectionDrainer = null;
				this.state.switchState(CONNECTON_STATE.CLOSED);
				return;
			}
		}
	}

	@Override
	public String toString () {
		return "MySQLConnection [serverName=" + this.serverName + ", login=" + this.login + "]";
	}

}
