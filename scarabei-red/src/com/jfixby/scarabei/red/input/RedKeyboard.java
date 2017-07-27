
package com.jfixby.scarabei.red.input;

import com.jfixby.scarabei.api.input.Key;
import com.jfixby.scarabei.api.input.Keyboard;
import com.jfixby.scarabei.api.input.KeysList;

public final class RedKeyboard implements Keyboard {

	@Override
	public KeysList listAllKeys () {
		return this.KEYS_LIST;
	}

// @Override
// public boolean isKeyPressed (final Key key) {
// return this.KEYS_LIST.isKeyPressed(key);
// }

	public final RedKeysList KEYS_LIST = new RedKeysList();;

	final Key ANY_KEY = new KeyCode("ANY_KEY", this); // -1;
	final Key NUM_0 = new KeyCode("0", this); // 7;
	final Key NUM_1 = new KeyCode("1", this); // 8;
	final Key NUM_2 = new KeyCode("2", this); // 9;
	final Key NUM_3 = new KeyCode("3", this); // 10;
	final Key NUM_4 = new KeyCode("4", this); // 11;
	final Key NUM_5 = new KeyCode("5", this); // 12;
	final Key NUM_6 = new KeyCode("6", this); // 13;
	final Key NUM_7 = new KeyCode("7", this); // 14;
	final Key NUM_8 = new KeyCode("8", this); // 15;
	final Key NUM_9 = new KeyCode("9", this); // 16;
	final Key A = new KeyCode("A", this); // 29;
	final Key ALT_LEFT = new KeyCode("L-Alt", this); // 57;
	final Key ALT_RIGHT = new KeyCode("R-Alt", this); // 58;
	final Key APOSTROPHE = new KeyCode("'", this); // 75;
	final Key AT = new KeyCode("@", this); // 77;
	final Key B = new KeyCode("B", this); // 30;
	final Key BACK = new KeyCode("Back", this); // 4;
	final Key BACKSLASH = new KeyCode("\\", this); // 73;
	final Key C = new KeyCode("C", this); // 31;
	final Key CALL = new KeyCode("Call", this); // 5;
	final Key CAMERA = new KeyCode("Camera", this); // 27;
	final Key CLEAR = new KeyCode("Clear", this); // 28;
	final Key COMMA = new KeyCode(",", this); // 55;
	final Key D = new KeyCode("D", this); // 32;

	/*
	 * These two keys work very different (weird) on different devices; Android slightly fucked up things here as usually. See hey
	 * have the same code. Be careful.
	 */
	final Key BACKSPACE = new KeyCode("BACKSPACE", this); // 67
	// final IKeyCode BACKWARD_DEL = new
	// KeyCode("Backward Delete", this); // 67

	final Key FORWARD_DEL = new KeyCode("Forward Delete", this); // 112;
	final Key DPAD_CENTER = new KeyCode("DPAD_CENTER", this); // 23;
	final Key DPAD_DOWN = new KeyCode("DPAD_DOWN", this); // 20;
	final Key DPAD_LEFT = new KeyCode("DPAD_LEFT", this); // 21;
	final Key DPAD_RIGHT = new KeyCode("DPAD_RIGHT", this); // 22;
	final Key DPAD_UP = new KeyCode("DPAD_UP", this); // 19;
	final Key CENTER = new KeyCode("Center", this); // 23;
	final Key DOWN = new KeyCode("Down", this); // 20;
	final Key LEFT = new KeyCode("Left", this); // 21;
	final Key RIGHT = new KeyCode("Right", this); // 22;
	final Key UP = new KeyCode("Up", this); // 19;
	final Key E = new KeyCode("E", this); // 33;
	final Key ENDCALL = new KeyCode("End Call", this); // 6;
	final Key ENTER = new KeyCode("Enter", this); // 66;
	final Key ENVELOPE = new KeyCode("Envelope", this); // 65;
	final Key EQUALS = new KeyCode("=", this); // 70;
	final Key EXPLORER = new KeyCode("Explorer", this); // 64;
	final Key F = new KeyCode("F", this); // 34;
	final Key FOCUS = new KeyCode("Focus", this); // 80;
	final Key G = new KeyCode("G", this); // 35;
	final Key GRAVE = new KeyCode("`", this); // 68;
	final Key H = new KeyCode("H", this); // 36;
	final Key HEADSETHOOK = new KeyCode("Headset Hook", this); // 79;
	final Key HOME = new KeyCode("Home", this); // 3;
	final Key I = new KeyCode("I", this); // 37;
	final Key J = new KeyCode("J", this); // 38;
	final Key K = new KeyCode("K", this); // 39;
	final Key L = new KeyCode("L", this); // 40;
	final Key LEFT_BRACKET = new KeyCode("[", this); // 71;
	final Key M = new KeyCode("M", this); // 41;
	final Key MEDIA_FAST_FORWARD = new KeyCode("Fast Forward", this); // 90;
	final Key MEDIA_NEXT = new KeyCode("Next Media", this); // 87;
	final Key MEDIA_PLAY_PAUSE = new KeyCode("Play/Pause", this); // 85;
	final Key MEDIA_PREVIOUS = new KeyCode("Prev Media", this); // 88;
	final Key MEDIA_REWIND = new KeyCode("Rewind", this); // 89;
	final Key MEDIA_STOP = new KeyCode("Stop Media", this); // 86;
	final Key MENU = new KeyCode("Menu", this); // 82;
	final Key MINUS = new KeyCode("-", this); // 69;
	final Key MUTE = new KeyCode("Mute", this); // 91;
	final Key N = new KeyCode("N", this); // 42;
	final Key NOTIFICATION = new KeyCode("Notification", this); // 83;
	final Key NUM = new KeyCode("Num", this); // 78;
	final Key O = new KeyCode("O", this); // 43;
	final Key P = new KeyCode("P", this); // 44;
	final Key PERIOD = new KeyCode(".", this); // 56;
	final Key PLUS = new KeyCode("Plus", this); // 81;
	final Key POUND = new KeyCode("#", this); // 18;
	final Key POWER = new KeyCode("Power", this); // 26;
	final Key Q = new KeyCode("Q", this); // 45;
	final Key R = new KeyCode("R", this); // 46;
	final Key RIGHT_BRACKET = new KeyCode("]", this); // 72;
	final Key S = new KeyCode("S", this); // 47;
	final Key SEARCH = new KeyCode("Search", this); // 84;
	final Key SEMICOLON = new KeyCode(";", this); // 74;
	final Key SHIFT_LEFT = new KeyCode("L-Shift", this); // 59;
	final Key SHIFT_RIGHT = new KeyCode("R-Shift", this); // 60;
	final Key SLASH = new KeyCode("/", this); // 76;
	final Key SOFT_LEFT = new KeyCode("Soft Left", this); // 1;
	final Key SOFT_RIGHT = new KeyCode("Soft Right", this); // 2;
	final Key SPACE = new KeyCode("Space", this); // 62;
	final Key STAR = new KeyCode("*", this); // 17;
	final Key SYM = new KeyCode("SYM", this); // 63;
	final Key T = new KeyCode("T", this); // 48;
	final Key TAB = new KeyCode("Tab", this); // 61;
	final Key U = new KeyCode("U", this); // 49;
	final Key UNKNOWN = new KeyCode("Unknown", this); // 0;
	final Key V = new KeyCode("V", this); // 50;
	final Key VOLUME_DOWN = new KeyCode("Volume Down", this); // 25;
	final Key VOLUME_UP = new KeyCode("Volume Up", this); // 24;
	final Key W = new KeyCode("W", this); // 51;
	final Key X = new KeyCode("X", this); // 52;
	final Key Y = new KeyCode("Y", this); // 53;
	final Key Z = new KeyCode("Z", this); // 54;
	final Key META_ALT_LEFT_ON = new KeyCode("META_ALT_LEFT_ON", this); // 16;
	final Key META_ALT_ON = new KeyCode("META_ALT_ON", this); // 2;
	final Key META_ALT_RIGHT_ON = new KeyCode("META_ALT_RIGHT_ON", this); // 32;
	final Key META_SHIFT_LEFT_ON = new KeyCode("META_SHIFT_LEFT_ON", this); // 64;
	final Key META_SHIFT_ON = new KeyCode("META_SHIFT_ON", this); // 1;
	final Key META_SHIFT_RIGHT_ON = new KeyCode("META_SHIFT_RIGHT_ON", this); // 128;
	final Key META_SYM_ON = new KeyCode("META_SYM_ON", this); // 4;
	final Key CONTROL_LEFT = new KeyCode("L-Ctrl", this); // 129;
	final Key CONTROL_RIGHT = new KeyCode("R-Ctrl", this); // 130;
	final Key ESCAPE = new KeyCode("Escape", this); // 131;
	final Key END = new KeyCode("End", this); // 132;
	final Key INSERT = new KeyCode("Insert", this); // 133;
	final Key PAGE_UP = new KeyCode("Page Up", this); // 92;
	final Key PAGE_DOWN = new KeyCode("Page Down", this); // 93;
	final Key PICTSYMBOLS = new KeyCode("PICTSYMBOLS", this); // 94;
	final Key SWITCH_CHARSET = new KeyCode("SWITCH_CHARSET", this); // 95;
	final Key BUTTON_CIRCLE = new KeyCode("BUTTON_CIRCLE", this); // 255;
	final Key BUTTON_A = new KeyCode("A Button", this); // 96;
	final Key BUTTON_B = new KeyCode("B Button", this); // 97;
	final Key BUTTON_C = new KeyCode("C Button", this); // 98;
	final Key BUTTON_X = new KeyCode("X Button", this); // 99;
	final Key BUTTON_Y = new KeyCode("Y Button", this); // 100;
	final Key BUTTON_Z = new KeyCode("Z Button", this); // 101;
	final Key BUTTON_L1 = new KeyCode("L1 Button", this); // 102;
	final Key BUTTON_R1 = new KeyCode("R1 Button", this); // 103;
	final Key BUTTON_L2 = new KeyCode("L2 Button", this); // 104;
	final Key BUTTON_R2 = new KeyCode("R2 Button", this); // 105;
	final Key BUTTON_THUMBL = new KeyCode("Left Thumb", this); // 106;
	final Key BUTTON_THUMBR = new KeyCode("Right Thumb", this); // 107;
	final Key BUTTON_START = new KeyCode("Start", this); // 108;
	final Key BUTTON_SELECT = new KeyCode("Select", this); // 109;
	final Key BUTTON_MODE = new KeyCode("Button Mode", this); // 110;

	final Key NUMPAD_0 = new KeyCode("Numpad 0", this); // 144;
	final Key NUMPAD_1 = new KeyCode("Numpad 1", this); // 145;
	final Key NUMPAD_2 = new KeyCode("Numpad 2", this); // 146;
	final Key NUMPAD_3 = new KeyCode("Numpad 3", this); // 147;
	final Key NUMPAD_4 = new KeyCode("Numpad 4", this); // 148;
	final Key NUMPAD_5 = new KeyCode("Numpad 5", this); // 149;
	final Key NUMPAD_6 = new KeyCode("Numpad 6", this); // 150;
	final Key NUMPAD_7 = new KeyCode("Numpad 7", this); // 151;
	final Key NUMPAD_8 = new KeyCode("Numpad 8", this); // 152;
	final Key NUMPAD_9 = new KeyCode("Numpad 9", this); // 153;

	final Key COLON = new KeyCode(":", this); // 243;
	final Key F1 = new KeyCode("F1", this); // 244;
	final Key F2 = new KeyCode("F2", this); // 245;
	final Key F3 = new KeyCode("F3", this); // 246;
	final Key F4 = new KeyCode("F4", this); // 247;
	final Key F5 = new KeyCode("F5", this); // 248;
	final Key F6 = new KeyCode("F6", this); // 249;
	final Key F7 = new KeyCode("F7", this); // 250;
	final Key F8 = new KeyCode("F8", this); // 251;
	final Key F9 = new KeyCode("F9", this); // 252;
	final Key F10 = new KeyCode("F10", this); // 253;
	final Key F11 = new KeyCode("F11", this); // 254;
	final Key F12 = new KeyCode("F12", this); // 255;
	final Key PRINT_SCREEN = new KeyCode("PrintScreen", this); // 255;

	@Override
	public Key ANY_KEY () {
		return this.ANY_KEY;
	}

	@Override
	public Key PRINT_SCREEN () {
		return this.PRINT_SCREEN;
	}

	@Override
	public Key NUM_0 () {
		return this.NUM_0;
	}

	@Override
	public Key NUM_1 () {
		return this.NUM_1;
	}

	@Override
	public Key NUM_2 () {
		return this.NUM_2;
	}

	@Override
	public Key NUM_3 () {
		return this.NUM_3;
	}

	@Override
	public Key NUM_4 () {
		return this.NUM_4;
	}

	@Override
	public Key NUM_5 () {
		return this.NUM_5;
	}

	@Override
	public Key NUM_6 () {
		return this.NUM_6;
	}

	@Override
	public Key NUM_7 () {
		return this.NUM_7;
	}

	@Override
	public Key NUM_8 () {
		return this.NUM_8;
	}

	@Override
	public Key NUM_9 () {
		return this.NUM_9;
	}

	@Override
	public Key A () {
		return this.A;
	}

	@Override
	public Key ALT_LEFT () {
		return this.ALT_LEFT;
	}

	@Override
	public Key ALT_RIGHT () {
		return this.ALT_RIGHT;
	}

	@Override
	public Key APOSTROPHE () {
		return this.APOSTROPHE;
	}

	@Override
	public Key AT () {
		return this.AT;
	}

	@Override
	public Key B () {
		return this.B;
	}

	@Override
	public Key BACK () {
		return this.BACK;
	}

	@Override
	public Key BACKSLASH () {
		return this.BACKSLASH;
	}

	@Override
	public Key C () {
		return this.C;
	}

	@Override
	public Key CALL () {
		return this.CALL;
	}

	@Override
	public Key CAMERA () {
		return this.CAMERA;
	}

	@Override
	public Key CLEAR () {
		return this.CLEAR;
	}

	@Override
	public Key COMMA () {
		return this.COMMA;
	}

	@Override
	public Key D () {
		return this.D;
	}

	@Override
	public Key BACKSPACE () {
		return this.BACKSPACE;
	}

	@Override
	public Key FORWARD_DEL () {
		return this.FORWARD_DEL;
	}

	@Override
	public Key DPAD_CENTER () {
		return this.DPAD_CENTER;
	}

	@Override
	public Key DPAD_DOWN () {
		return this.DPAD_DOWN;
	}

	@Override
	public Key DPAD_LEFT () {
		return this.DPAD_LEFT;
	}

	@Override
	public Key DPAD_RIGHT () {
		return this.DPAD_RIGHT;
	}

	@Override
	public Key DPAD_UP () {
		return this.DPAD_UP;
	}

	@Override
	public Key CENTER () {
		return this.CENTER;
	}

	@Override
	public Key DOWN () {
		return this.DOWN;
	}

	@Override
	public Key LEFT () {
		return this.LEFT;
	}

	@Override
	public Key RIGHT () {
		return this.RIGHT;
	}

	@Override
	public Key UP () {
		return this.UP;
	}

	@Override
	public Key E () {
		return this.E;
	}

	@Override
	public Key ENDCALL () {
		return this.ENDCALL;
	}

	@Override
	public Key ENTER () {
		return this.ENTER;
	}

	@Override
	public Key ENVELOPE () {
		return this.ENVELOPE;
	}

	@Override
	public Key EQUALS () {
		return this.EQUALS;
	}

	@Override
	public Key EXPLORER () {
		return this.EXPLORER;
	}

	@Override
	public Key F () {
		return this.F;
	}

	@Override
	public Key FOCUS () {
		return this.FOCUS;
	}

	@Override
	public Key G () {
		return this.G;
	}

	@Override
	public Key GRAVE () {
		return this.GRAVE;
	}

	@Override
	public Key H () {
		return this.H;
	}

	@Override
	public Key HEADSETHOOK () {
		return this.HEADSETHOOK;
	}

	@Override
	public Key HOME () {
		return this.HOME;
	}

	@Override
	public Key I () {
		return this.I;
	}

	@Override
	public Key J () {
		return this.J;
	}

	@Override
	public Key K () {
		return this.K;
	}

	@Override
	public Key L () {
		return this.L;
	}

	@Override
	public Key LEFT_BRACKET () {
		return this.LEFT_BRACKET;
	}

	@Override
	public Key M () {
		return this.M;
	}

	@Override
	public Key MEDIA_FAST_FORWARD () {
		return this.MEDIA_FAST_FORWARD;
	}

	@Override
	public Key MEDIA_NEXT () {
		return this.MEDIA_FAST_FORWARD;
	}

	@Override
	public Key MEDIA_PLAY_PAUSE () {
		return this.MEDIA_PLAY_PAUSE;
	}

	@Override
	public Key MEDIA_PREVIOUS () {
		return this.MEDIA_PREVIOUS;
	}

	@Override
	public Key MEDIA_REWIND () {
		return this.MEDIA_REWIND;
	}

	@Override
	public Key MEDIA_STOP () {
		return this.MEDIA_STOP;
	}

	@Override
	public Key MENU () {
		return this.MENU;
	}

	@Override
	public Key MINUS () {
		return this.MINUS;
	}

	@Override
	public Key MUTE () {
		return this.MUTE;
	}

	@Override
	public Key N () {
		return this.N;
	}

	@Override
	public Key NOTIFICATION () {
		return this.NOTIFICATION;
	}

	@Override
	public Key NUM () {
		return this.NUM;
	}

	@Override
	public Key O () {
		return this.O;
	}

	@Override
	public Key P () {
		return this.P;
	}

	@Override
	public Key PERIOD () {
		return this.PERIOD;
	}

	@Override
	public Key PLUS () {
		return this.PLUS;
	}

	@Override
	public Key POUND () {
		return this.POUND;
	}

	@Override
	public Key POWER () {
		return this.POWER;
	}

	@Override
	public Key Q () {
		return this.Q;
	}

	@Override
	public Key R () {
		return this.R;
	}

	@Override
	public Key RIGHT_BRACKET () {
		return this.RIGHT_BRACKET;
	}

	@Override
	public Key S () {
		return this.S;
	}

	@Override
	public Key SEARCH () {
		return this.SEARCH;
	}

	@Override
	public Key SEMICOLON () {
		return this.SEMICOLON;
	}

	@Override
	public Key SHIFT_LEFT () {
		return this.SHIFT_LEFT;
	}

	@Override
	public Key SHIFT_RIGHT () {
		return this.SHIFT_RIGHT;
	}

	@Override
	public Key SLASH () {
		return this.SLASH;
	}

	@Override
	public Key SOFT_LEFT () {
		return this.SOFT_LEFT;
	}

	@Override
	public Key SPACE () {
		return this.SPACE;
	}

	@Override
	public Key SOFT_RIGHT () {
		return this.SOFT_RIGHT;
	}

	@Override
	public Key SYM () {
		return this.SYM;
	}

	@Override
	public Key T () {
		return this.T;
	}

	@Override
	public Key TAB () {
		return this.TAB;
	}

	@Override
	public Key U () {
		return this.U;
	}

	@Override
	public Key STAR () {
		return this.STAR;
	}

	@Override
	public Key UNKNOWN () {
		return this.UNKNOWN;
	}

	@Override
	public Key V () {
		return this.V;
	}

	@Override
	public Key VOLUME_DOWN () {
		return this.VOLUME_DOWN;
	}

	@Override
	public Key VOLUME_UP () {
		return this.VOLUME_UP;
	}

	@Override
	public Key W () {
		return this.W;
	}

	@Override
	public Key X () {
		return this.X;
	}

	@Override
	public Key Y () {
		return this.Y;
	}

	@Override
	public Key Z () {
		return this.Z;
	}

	@Override
	public Key META_ALT_LEFT_ON () {
		return this.META_ALT_LEFT_ON;
	}

	@Override
	public Key META_ALT_ON () {
		return this.META_ALT_ON;
	}

	@Override
	public Key META_ALT_RIGHT_ON () {
		return this.META_ALT_RIGHT_ON;
	}

	@Override
	public Key META_SHIFT_LEFT_ON () {
		return this.META_SHIFT_LEFT_ON;
	}

	@Override
	public Key META_SHIFT_ON () {
		return this.META_SHIFT_ON;
	}

	@Override
	public Key META_SHIFT_RIGHT_ON () {
		return this.META_SHIFT_RIGHT_ON;
	}

	@Override
	public Key META_SYM_ON () {
		return this.META_SYM_ON;
	}

	@Override
	public Key CONTROL_LEFT () {
		return this.CONTROL_LEFT;
	}

	@Override
	public Key CONTROL_RIGHT () {
		return this.CONTROL_RIGHT;
	}

	@Override
	public Key ESCAPE () {
		return this.ESCAPE;
	}

	@Override
	public Key END () {
		return this.END;
	}

	@Override
	public Key INSERT () {
		return this.INSERT;
	}

	@Override
	public Key PAGE_UP () {
		return this.PAGE_UP;
	}

	@Override
	public Key PAGE_DOWN () {
		return this.PAGE_DOWN;
	}

	@Override
	public Key PICTSYMBOLS () {
		return this.PICTSYMBOLS;
	}

	@Override
	public Key SWITCH_CHARSET () {
		return this.SWITCH_CHARSET;
	}

	@Override
	public Key BUTTON_CIRCLE () {
		return this.BUTTON_CIRCLE;
	}

	@Override
	public Key BUTTON_A () {
		return this.BUTTON_A;
	}

	@Override
	public Key BUTTON_B () {
		return this.BUTTON_B;
	}

	@Override
	public Key BUTTON_C () {
		return this.BUTTON_C;
	}

	@Override
	public Key BUTTON_X () {
		return this.BUTTON_X;
	}

	@Override
	public Key BUTTON_Y () {
		return this.BUTTON_Y;
	}

	@Override
	public Key BUTTON_Z () {
		return this.BUTTON_Z;
	}

	@Override
	public Key BUTTON_L1 () {
		return this.BUTTON_L1;
	}

	@Override
	public Key BUTTON_R1 () {
		return this.BUTTON_R1;
	}

	@Override
	public Key BUTTON_R2 () {
		return this.BUTTON_R2;
	}

	@Override
	public Key BUTTON_L2 () {
		return this.BUTTON_L2;
	}

	@Override
	public Key BUTTON_THUMBL () {
		return this.BUTTON_THUMBL;
	}

	@Override
	public Key BUTTON_START () {
		return this.BUTTON_START;
	}

	@Override
	public Key BUTTON_MODE () {
		return this.BUTTON_MODE;
	}

	@Override
	public Key BUTTON_SELECT () {
		return this.BUTTON_SELECT;
	}

	@Override
	public Key BUTTON_THUMBR () {
		return this.BUTTON_THUMBR;
	}

	@Override
	public Key NUMPAD_0 () {
		return this.NUMPAD_0;
	}

	@Override
	public Key NUMPAD_1 () {
		return this.NUMPAD_1;
	}

	@Override
	public Key NUMPAD_2 () {
		return this.NUMPAD_2;
	}

	@Override
	public Key NUMPAD_3 () {
		return this.NUMPAD_3;
	}

	@Override
	public Key NUMPAD_4 () {
		return this.NUMPAD_4;
	}

	@Override
	public Key NUMPAD_5 () {
		return this.NUMPAD_5;
	}

	@Override
	public Key NUMPAD_6 () {
		return this.NUMPAD_6;
	}

	@Override
	public Key NUMPAD_7 () {
		return this.NUMPAD_7;
	}

	@Override
	public Key NUMPAD_8 () {
		return this.NUMPAD_8;
	}

	@Override
	public Key NUMPAD_9 () {
		return this.NUMPAD_9;
	}

	@Override
	public Key COLON () {
		return this.COLON;
	}

	@Override
	public Key F1 () {
		return this.F1;
	}

	@Override
	public Key F2 () {
		return this.F2;
	}

	@Override
	public Key F3 () {
		return this.F3;
	}

	@Override
	public Key F4 () {
		return this.F4;
	}

	@Override
	public Key F5 () {
		return this.F5;
	}

	@Override
	public Key F6 () {
		return this.F6;
	}

	@Override
	public Key F7 () {
		return this.F7;
	}

	@Override
	public Key F8 () {
		return this.F8;
	}

	@Override
	public Key F9 () {
		return this.F9;
	}

	@Override
	public Key F10 () {
		return this.F10;
	}

	@Override
	public Key F11 () {
		return this.F11;
	}

	@Override
	public Key F12 () {
		return this.F12;
	}

}
