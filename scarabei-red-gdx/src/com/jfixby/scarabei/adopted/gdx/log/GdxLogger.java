
package com.jfixby.scarabei.adopted.gdx.log;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.LoggerComponent;

public class GdxLogger implements LoggerComponent {

	public GdxLogger () {
		super();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Err.throwNotImplementedYet();
	}

	@Override
	public void d (final Object msg) {
	}

	@Override
	public void d (final Object... msg) {
	}

	@Override
	public void d (final Object tag, final Object msg) {
	}

	@Override
	public void e (final Object msg) {
	}

	@Override
	public void e (final Object tag, final Object msg) {
	}

	@Override
	public void e (final Object tag, final Throwable err) {
	}

	@Override
	public void d () {
	}

	@Override
	public void e () {
	}

	@Override
	public void d_appendChars (final Object msg) {
	}

	@Override
	public String toString (final Object[] array) {
		return null;
	}

	@Override
	public String stackTraceToString (final Throwable e) {
		return null;
	}

	@Override
	public void e (final Throwable msg) {
	}

}
