package com.jfixby.red.input;

import com.jfixby.cmns.api.input.Key;
import com.jfixby.cmns.api.input.Keyboard;
import com.jfixby.cmns.api.input.KeysList;

public final class RedKeyboard implements Keyboard {

	@Override
	public KeysList listAllKeys() {
		return this.KEYS_LIST;
	}

	public final KeysList KEYS_LIST = new RedKeysList();;

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
	 * These two keys work very different (weird) on different devices; Android
	 * slightly fucked up things here as usually. See hey have the same code. Be
	 * careful.
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

	@Override
	public Key ANY_KEY() {
		return this.ANY_KEY;
	}

	@Override
	public Key NUM_0() {
		return this.NUM_0;
	}

	@Override
	public Key NUM_1() {
		return NUM_1;
	}

	@Override
	public Key NUM_2() {
		return NUM_2;
	}

	@Override
	public Key NUM_3() {
		return NUM_3;
	}

	@Override
	public Key NUM_4() {
		return NUM_4;
	}

	@Override
	public Key NUM_5() {
		return NUM_5;
	}

	@Override
	public Key NUM_6() {
		return NUM_6;
	}

	@Override
	public Key NUM_7() {
		return NUM_7;
	}

	@Override
	public Key NUM_8() {
		return NUM_8;
	}

	@Override
	public Key NUM_9() {
		return NUM_9;
	}

	@Override
	public Key A() {
		return A;
	}

	@Override
	public Key ALT_LEFT() {
		return ALT_LEFT;
	}

	@Override
	public Key ALT_RIGHT() {
		return ALT_RIGHT;
	}

	@Override
	public Key APOSTROPHE() {
		return APOSTROPHE;
	}

	@Override
	public Key AT() {
		return AT;
	}

	@Override
	public Key B() {
		return B;
	}

	@Override
	public Key BACK() {
		return BACK;
	}

	@Override
	public Key BACKSLASH() {
		return BACKSLASH;
	}

	@Override
	public Key C() {
		return C;
	}

	@Override
	public Key CALL() {
		return CALL;
	}

	@Override
	public Key CAMERA() {
		return CAMERA;
	}

	@Override
	public Key CLEAR() {
		return CLEAR;
	}

	@Override
	public Key COMMA() {
		return COMMA;
	}

	@Override
	public Key D() {
		return D;
	}

	@Override
	public Key BACKSPACE() {
		return BACKSPACE;
	}

	@Override
	public Key FORWARD_DEL() {
		return FORWARD_DEL;
	}

	@Override
	public Key DPAD_CENTER() {
		return DPAD_CENTER;
	}

	@Override
	public Key DPAD_DOWN() {
		return DPAD_DOWN;
	}

	@Override
	public Key DPAD_LEFT() {
		return DPAD_LEFT;
	}

	@Override
	public Key DPAD_RIGHT() {
		return DPAD_RIGHT;
	}

	@Override
	public Key DPAD_UP() {
		return DPAD_UP;
	}

	@Override
	public Key CENTER() {
		return CENTER;
	}

	@Override
	public Key DOWN() {
		return DOWN;
	}

	@Override
	public Key LEFT() {
		return LEFT;
	}

	@Override
	public Key RIGHT() {
		return RIGHT;
	}

	@Override
	public Key UP() {
		return UP;
	}

	@Override
	public Key E() {
		return E;
	}

	@Override
	public Key ENDCALL() {
		return ENDCALL;
	}

	@Override
	public Key ENTER() {
		return ENTER;
	}

	@Override
	public Key ENVELOPE() {
		return ENVELOPE;
	}

	@Override
	public Key EQUALS() {
		return EQUALS;
	}

	@Override
	public Key EXPLORER() {
		return EXPLORER;
	}

	@Override
	public Key F() {
		return F;
	}

	@Override
	public Key FOCUS() {
		return FOCUS;
	}

	@Override
	public Key G() {
		return G;
	}

	@Override
	public Key GRAVE() {
		return GRAVE;
	}

	@Override
	public Key H() {
		return H;
	}

	@Override
	public Key HEADSETHOOK() {
		return HEADSETHOOK;
	}

	@Override
	public Key HOME() {
		return HOME;
	}

	@Override
	public Key I() {
		return I;
	}

	@Override
	public Key J() {
		return J;
	}

	@Override
	public Key K() {
		return K;
	}

	@Override
	public Key L() {
		return L;
	}

	@Override
	public Key LEFT_BRACKET() {
		return LEFT_BRACKET;
	}

	@Override
	public Key M() {
		return M;
	}

	@Override
	public Key MEDIA_FAST_FORWARD() {
		return MEDIA_FAST_FORWARD;
	}

	@Override
	public Key MEDIA_NEXT() {
		return MEDIA_FAST_FORWARD;
	}

	@Override
	public Key MEDIA_PLAY_PAUSE() {
		return MEDIA_PLAY_PAUSE;
	}

	@Override
	public Key MEDIA_PREVIOUS() {
		return MEDIA_PREVIOUS;
	}

	@Override
	public Key MEDIA_REWIND() {
		return MEDIA_REWIND;
	}

	@Override
	public Key MEDIA_STOP() {
		return MEDIA_STOP;
	}

	@Override
	public Key MENU() {
		return MENU;
	}

	@Override
	public Key MINUS() {
		return MINUS;
	}

	@Override
	public Key MUTE() {
		return MUTE;
	}

	@Override
	public Key N() {
		return N;
	}

	@Override
	public Key NOTIFICATION() {
		return NOTIFICATION;
	}

	@Override
	public Key NUM() {
		return NUM;
	}

	@Override
	public Key O() {
		return O;
	}

	@Override
	public Key P() {
		return P;
	}

	@Override
	public Key PERIOD() {
		return PERIOD;
	}

	@Override
	public Key PLUS() {
		return PLUS;
	}

	@Override
	public Key POUND() {
		return POUND;
	}

	@Override
	public Key POWER() {
		return POWER;
	}

	@Override
	public Key Q() {
		return Q;
	}

	@Override
	public Key R() {
		return R;
	}

	@Override
	public Key RIGHT_BRACKET() {
		return RIGHT_BRACKET;
	}

	@Override
	public Key S() {
		return S;
	}

	@Override
	public Key SEARCH() {
		return SEARCH;
	}

	@Override
	public Key SEMICOLON() {
		return SEMICOLON;
	}

	@Override
	public Key SHIFT_LEFT() {
		return SHIFT_LEFT;
	}

	@Override
	public Key SHIFT_RIGHT() {
		return SHIFT_RIGHT;
	}

	@Override
	public Key SLASH() {
		return SLASH;
	}

	@Override
	public Key SOFT_LEFT() {
		return SOFT_LEFT;
	}

	@Override
	public Key SPACE() {
		return SPACE;
	}

	@Override
	public Key SOFT_RIGHT() {
		return SOFT_RIGHT;
	}

	@Override
	public Key SYM() {
		return SYM;
	}

	@Override
	public Key T() {
		return T;
	}

	@Override
	public Key TAB() {
		return TAB;
	}

	@Override
	public Key U() {
		return U;
	}

	@Override
	public Key STAR() {
		return STAR;
	}

	@Override
	public Key UNKNOWN() {
		return UNKNOWN;
	}

	@Override
	public Key V() {
		return V;
	}

	@Override
	public Key VOLUME_DOWN() {
		return VOLUME_DOWN;
	}

	@Override
	public Key VOLUME_UP() {
		return VOLUME_UP;
	}

	@Override
	public Key W() {
		return W;
	}

	@Override
	public Key X() {
		return X;
	}

	@Override
	public Key Y() {
		return Y;
	}

	@Override
	public Key Z() {
		return Z;
	}

	@Override
	public Key META_ALT_LEFT_ON() {
		return META_ALT_LEFT_ON;
	}

	@Override
	public Key META_ALT_ON() {
		return META_ALT_ON;
	}

	@Override
	public Key META_ALT_RIGHT_ON() {
		return META_ALT_RIGHT_ON;
	}

	@Override
	public Key META_SHIFT_LEFT_ON() {
		return META_SHIFT_LEFT_ON;
	}

	@Override
	public Key META_SHIFT_ON() {
		return META_SHIFT_ON;
	}

	@Override
	public Key META_SHIFT_RIGHT_ON() {
		return META_SHIFT_RIGHT_ON;
	}

	@Override
	public Key META_SYM_ON() {
		return META_SYM_ON;
	}

	@Override
	public Key CONTROL_LEFT() {
		return CONTROL_LEFT;
	}

	@Override
	public Key CONTROL_RIGHT() {
		return CONTROL_RIGHT;
	}

	@Override
	public Key ESCAPE() {
		return ESCAPE;
	}

	@Override
	public Key END() {
		return END;
	}

	@Override
	public Key INSERT() {
		return INSERT;
	}

	@Override
	public Key PAGE_UP() {
		return PAGE_UP;
	}

	@Override
	public Key PAGE_DOWN() {
		return PAGE_DOWN;
	}

	@Override
	public Key PICTSYMBOLS() {
		return PICTSYMBOLS;
	}

	@Override
	public Key SWITCH_CHARSET() {
		return SWITCH_CHARSET;
	}

	@Override
	public Key BUTTON_CIRCLE() {
		return BUTTON_CIRCLE;
	}

	@Override
	public Key BUTTON_A() {
		return BUTTON_A;
	}

	@Override
	public Key BUTTON_B() {
		return BUTTON_B;
	}

	@Override
	public Key BUTTON_C() {
		return BUTTON_C;
	}

	@Override
	public Key BUTTON_X() {
		return BUTTON_X;
	}

	@Override
	public Key BUTTON_Y() {
		return BUTTON_Y;
	}

	@Override
	public Key BUTTON_Z() {
		return BUTTON_Z;
	}

	@Override
	public Key BUTTON_L1() {
		return BUTTON_L1;
	}

	@Override
	public Key BUTTON_R1() {
		return BUTTON_R1;
	}

	@Override
	public Key BUTTON_R2() {
		return BUTTON_R2;
	}

	@Override
	public Key BUTTON_L2() {
		return BUTTON_L2;
	}

	@Override
	public Key BUTTON_THUMBL() {
		return BUTTON_THUMBL;
	}

	@Override
	public Key BUTTON_START() {
		return BUTTON_START;
	}

	@Override
	public Key BUTTON_MODE() {
		return BUTTON_MODE;
	}

	@Override
	public Key BUTTON_SELECT() {
		return BUTTON_SELECT;
	}

	@Override
	public Key BUTTON_THUMBR() {
		return BUTTON_THUMBR;
	}

	@Override
	public Key NUMPAD_0() {
		return NUMPAD_0;
	}

	@Override
	public Key NUMPAD_1() {
		return NUMPAD_1;
	}

	@Override
	public Key NUMPAD_2() {
		return NUMPAD_2;
	}

	@Override
	public Key NUMPAD_3() {
		return NUMPAD_3;
	}

	@Override
	public Key NUMPAD_4() {
		return NUMPAD_4;
	}

	@Override
	public Key NUMPAD_5() {
		return NUMPAD_5;
	}

	@Override
	public Key NUMPAD_6() {
		return NUMPAD_6;
	}

	@Override
	public Key NUMPAD_7() {
		return NUMPAD_7;
	}

	@Override
	public Key NUMPAD_8() {
		return NUMPAD_8;
	}

	@Override
	public Key NUMPAD_9() {
		return NUMPAD_9;
	}

	@Override
	public Key COLON() {
		return COLON;
	}

	@Override
	public Key F1() {
		return F1;
	}

	@Override
	public Key F2() {
		return F2;
	}

	@Override
	public Key F3() {
		return F3;
	}

	@Override
	public Key F4() {
		return F4;
	}

	@Override
	public Key F5() {
		return F5;
	}

	@Override
	public Key F6() {
		return F6;
	}

	@Override
	public Key F7() {
		return F7;
	}

	@Override
	public Key F8() {
		return F8;
	}

	@Override
	public Key F9() {
		return F9;
	}

	@Override
	public Key F10() {
		return F10;
	}

	@Override
	public Key F11() {
		return F11;
	}

	@Override
	public Key F12() {
		return F12;
	}

}
