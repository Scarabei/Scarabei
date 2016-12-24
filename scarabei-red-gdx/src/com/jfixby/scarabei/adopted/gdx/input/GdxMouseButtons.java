package com.jfixby.scarabei.adopted.gdx.input;

import com.jfixby.scarabei.api.input.MouseButton;
import com.jfixby.scarabei.api.input.UserInput;

public final class GdxMouseButtons {

	public static MouseButton resolveGdxMouseButtonCode(int gdx_button_code) {
		if (com.badlogic.gdx.Input.Buttons.LEFT == gdx_button_code) {
			return UserInput.MouseButtons().LEFT();
		}
		if (com.badlogic.gdx.Input.Buttons.RIGHT == gdx_button_code) {
			return UserInput.MouseButtons().RIGHT();
		}
		if (com.badlogic.gdx.Input.Buttons.MIDDLE == gdx_button_code) {
			return UserInput.MouseButtons().MIDDLE();
		}
		return null;

	}

}
