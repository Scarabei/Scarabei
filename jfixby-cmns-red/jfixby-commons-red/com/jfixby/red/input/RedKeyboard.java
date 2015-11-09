package com.jfixby.red.input;

import com.jfixby.cmns.api.input.Key;
import com.jfixby.cmns.api.input.Keyboard;
import com.jfixby.cmns.api.input.KeysList;

public final class RedKeyboard implements Keyboard {

	public final KeysList KEYS_LIST = new RedKeysList();;

	final Key ANY_KEY = new RedKeyCode("ANY_KEY", this); // -1;
	final Key NUM_0 = new RedKeyCode("0", this); // 7;
	final Key NUM_1 = new RedKeyCode("1", this); // 8;
	final Key NUM_2 = new RedKeyCode("2", this); // 9;
	final Key NUM_3 = new RedKeyCode("3", this); // 10;
	final Key NUM_4 = new RedKeyCode("4", this); // 11;
	final Key NUM_5 = new RedKeyCode("5", this); // 12;
	final Key NUM_6 = new RedKeyCode("6", this); // 13;
	final Key NUM_7 = new RedKeyCode("7", this); // 14;
	final Key NUM_8 = new RedKeyCode("8", this); // 15;
	final Key NUM_9 = new RedKeyCode("9", this); // 16;
	final Key A = new RedKeyCode("A", this); // 29;
	final Key ALT_LEFT = new RedKeyCode("L-Alt", this); // 57;
	final Key ALT_RIGHT = new RedKeyCode("R-Alt", this); // 58;
	final Key APOSTROPHE = new RedKeyCode("'", this); // 75;
	final Key AT = new RedKeyCode("@", this); // 77;
	final Key B = new RedKeyCode("B", this); // 30;
	final Key BACK = new RedKeyCode("Back", this); // 4;
	final Key BACKSLASH = new RedKeyCode("\\", this); // 73;
	final Key C = new RedKeyCode("C", this); // 31;
	final Key CALL = new RedKeyCode("Call", this); // 5;
	final Key CAMERA = new RedKeyCode("Camera", this); // 27;
	final Key CLEAR = new RedKeyCode("Clear", this); // 28;
	final Key COMMA = new RedKeyCode(",", this); // 55;
	final Key D = new RedKeyCode("D", this); // 32;

	/*
	 * These two keys work very different (weird) on different devices; Android
	 * slightly fucked up things here as usually. See hey have the same code. Be
	 * careful.
	 */
	final Key BACKSPACE = new RedKeyCode("BACKSPACE", this); // 67
	// final IKeyCode BACKWARD_DEL = new
	// KeyCode("Backward Delete", this); // 67

	final Key FORWARD_DEL = new RedKeyCode("Forward Delete", this); // 112;
	final Key DPAD_CENTER = new RedKeyCode("DPAD_CENTER", this); // 23;
	final Key DPAD_DOWN = new RedKeyCode("DPAD_DOWN", this); // 20;
	final Key DPAD_LEFT = new RedKeyCode("DPAD_LEFT", this); // 21;
	final Key DPAD_RIGHT = new RedKeyCode("DPAD_RIGHT", this); // 22;
	final Key DPAD_UP = new RedKeyCode("DPAD_UP", this); // 19;
	final Key CENTER = new RedKeyCode("Center", this); // 23;
	final Key DOWN = new RedKeyCode("Down", this); // 20;
	final Key LEFT = new RedKeyCode("Left", this); // 21;
	final Key RIGHT = new RedKeyCode("Right", this); // 22;
	final Key UP = new RedKeyCode("Up", this); // 19;
	final Key E = new RedKeyCode("E", this); // 33;
	final Key ENDCALL = new RedKeyCode("End Call", this); // 6;
	final Key ENTER = new RedKeyCode("Enter", this); // 66;
	final Key ENVELOPE = new RedKeyCode("Envelope", this); // 65;
	final Key EQUALS = new RedKeyCode("=", this); // 70;
	final Key EXPLORER = new RedKeyCode("Explorer", this); // 64;
	final Key F = new RedKeyCode("F", this); // 34;
	final Key FOCUS = new RedKeyCode("Focus", this); // 80;
	final Key G = new RedKeyCode("G", this); // 35;
	final Key GRAVE = new RedKeyCode("`", this); // 68;
	final Key H = new RedKeyCode("H", this); // 36;
	final Key HEADSETHOOK = new RedKeyCode("Headset Hook", this); // 79;
	final Key HOME = new RedKeyCode("Home", this); // 3;
	final Key I = new RedKeyCode("I", this); // 37;
	final Key J = new RedKeyCode("J", this); // 38;
	final Key K = new RedKeyCode("K", this); // 39;
	final Key L = new RedKeyCode("L", this); // 40;
	final Key LEFT_BRACKET = new RedKeyCode("[", this); // 71;
	final Key M = new RedKeyCode("M", this); // 41;
	final Key MEDIA_FAST_FORWARD = new RedKeyCode("Fast Forward", this); // 90;
	final Key MEDIA_NEXT = new RedKeyCode("Next Media", this); // 87;
	final Key MEDIA_PLAY_PAUSE = new RedKeyCode("Play/Pause", this); // 85;
	final Key MEDIA_PREVIOUS = new RedKeyCode("Prev Media", this); // 88;
	final Key MEDIA_REWIND = new RedKeyCode("Rewind", this); // 89;
	final Key MEDIA_STOP = new RedKeyCode("Stop Media", this); // 86;
	final Key MENU = new RedKeyCode("Menu", this); // 82;
	final Key MINUS = new RedKeyCode("-", this); // 69;
	final Key MUTE = new RedKeyCode("Mute", this); // 91;
	final Key N = new RedKeyCode("N", this); // 42;
	final Key NOTIFICATION = new RedKeyCode("Notification", this); // 83;
	final Key NUM = new RedKeyCode("Num", this); // 78;
	final Key O = new RedKeyCode("O", this); // 43;
	final Key P = new RedKeyCode("P", this); // 44;
	final Key PERIOD = new RedKeyCode(".", this); // 56;
	final Key PLUS = new RedKeyCode("Plus", this); // 81;
	final Key POUND = new RedKeyCode("#", this); // 18;
	final Key POWER = new RedKeyCode("Power", this); // 26;
	final Key Q = new RedKeyCode("Q", this); // 45;
	final Key R = new RedKeyCode("R", this); // 46;
	final Key RIGHT_BRACKET = new RedKeyCode("]", this); // 72;
	final Key S = new RedKeyCode("S", this); // 47;
	final Key SEARCH = new RedKeyCode("Search", this); // 84;
	final Key SEMICOLON = new RedKeyCode(";", this); // 74;
	final Key SHIFT_LEFT = new RedKeyCode("L-Shift", this); // 59;
	final Key SHIFT_RIGHT = new RedKeyCode("R-Shift", this); // 60;
	final Key SLASH = new RedKeyCode("/", this); // 76;
	final Key SOFT_LEFT = new RedKeyCode("Soft Left", this); // 1;
	final Key SOFT_RIGHT = new RedKeyCode("Soft Right", this); // 2;
	final Key SPACE = new RedKeyCode("Space", this); // 62;
	final Key STAR = new RedKeyCode("*", this); // 17;
	final Key SYM = new RedKeyCode("SYM", this); // 63;
	final Key T = new RedKeyCode("T", this); // 48;
	final Key TAB = new RedKeyCode("Tab", this); // 61;
	final Key U = new RedKeyCode("U", this); // 49;
	final Key UNKNOWN = new RedKeyCode("Unknown", this); // 0;
	final Key V = new RedKeyCode("V", this); // 50;
	final Key VOLUME_DOWN = new RedKeyCode("Volume Down", this); // 25;
	final Key VOLUME_UP = new RedKeyCode("Volume Up", this); // 24;
	final Key W = new RedKeyCode("W", this); // 51;
	final Key X = new RedKeyCode("X", this); // 52;
	final Key Y = new RedKeyCode("Y", this); // 53;
	final Key Z = new RedKeyCode("Z", this); // 54;
	final Key META_ALT_LEFT_ON = new RedKeyCode("META_ALT_LEFT_ON", this); // 16;
	final Key META_ALT_ON = new RedKeyCode("META_ALT_ON", this); // 2;
	final Key META_ALT_RIGHT_ON = new RedKeyCode("META_ALT_RIGHT_ON", this); // 32;
	final Key META_SHIFT_LEFT_ON = new RedKeyCode("META_SHIFT_LEFT_ON", this); // 64;
	final Key META_SHIFT_ON = new RedKeyCode("META_SHIFT_ON", this); // 1;
	final Key META_SHIFT_RIGHT_ON = new RedKeyCode("META_SHIFT_RIGHT_ON", this); // 128;
	final Key META_SYM_ON = new RedKeyCode("META_SYM_ON", this); // 4;
	final Key CONTROL_LEFT = new RedKeyCode("L-Ctrl", this); // 129;
	final Key CONTROL_RIGHT = new RedKeyCode("R-Ctrl", this); // 130;
	final Key ESCAPE = new RedKeyCode("Escape", this); // 131;
	final Key END = new RedKeyCode("End", this); // 132;
	final Key INSERT = new RedKeyCode("Insert", this); // 133;
	final Key PAGE_UP = new RedKeyCode("Page Up", this); // 92;
	final Key PAGE_DOWN = new RedKeyCode("Page Down", this); // 93;
	final Key PICTSYMBOLS = new RedKeyCode("PICTSYMBOLS", this); // 94;
	final Key SWITCH_CHARSET = new RedKeyCode("SWITCH_CHARSET", this); // 95;
	final Key BUTTON_CIRCLE = new RedKeyCode("BUTTON_CIRCLE", this); // 255;
	final Key BUTTON_A = new RedKeyCode("A Button", this); // 96;
	final Key BUTTON_B = new RedKeyCode("B Button", this); // 97;
	final Key BUTTON_C = new RedKeyCode("C Button", this); // 98;
	final Key BUTTON_X = new RedKeyCode("X Button", this); // 99;
	final Key BUTTON_Y = new RedKeyCode("Y Button", this); // 100;
	final Key BUTTON_Z = new RedKeyCode("Z Button", this); // 101;
	final Key BUTTON_L1 = new RedKeyCode("L1 Button", this); // 102;
	final Key BUTTON_R1 = new RedKeyCode("R1 Button", this); // 103;
	final Key BUTTON_L2 = new RedKeyCode("L2 Button", this); // 104;
	final Key BUTTON_R2 = new RedKeyCode("R2 Button", this); // 105;
	final Key BUTTON_THUMBL = new RedKeyCode("Left Thumb", this); // 106;
	final Key BUTTON_THUMBR = new RedKeyCode("Right Thumb", this); // 107;
	final Key BUTTON_START = new RedKeyCode("Start", this); // 108;
	final Key BUTTON_SELECT = new RedKeyCode("Select", this); // 109;
	final Key BUTTON_MODE = new RedKeyCode("Button Mode", this); // 110;

	final Key NUMPAD_0 = new RedKeyCode("Numpad 0", this); // 144;
	final Key NUMPAD_1 = new RedKeyCode("Numpad 1", this); // 145;
	final Key NUMPAD_2 = new RedKeyCode("Numpad 2", this); // 146;
	final Key NUMPAD_3 = new RedKeyCode("Numpad 3", this); // 147;
	final Key NUMPAD_4 = new RedKeyCode("Numpad 4", this); // 148;
	final Key NUMPAD_5 = new RedKeyCode("Numpad 5", this); // 149;
	final Key NUMPAD_6 = new RedKeyCode("Numpad 6", this); // 150;
	final Key NUMPAD_7 = new RedKeyCode("Numpad 7", this); // 151;
	final Key NUMPAD_8 = new RedKeyCode("Numpad 8", this); // 152;
	final Key NUMPAD_9 = new RedKeyCode("Numpad 9", this); // 153;

	final Key COLON = new RedKeyCode(":", this); // 243;
	final Key F1 = new RedKeyCode("F1", this); // 244;
	final Key F2 = new RedKeyCode("F2", this); // 245;
	final Key F3 = new RedKeyCode("F3", this); // 246;
	final Key F4 = new RedKeyCode("F4", this); // 247;
	final Key F5 = new RedKeyCode("F5", this); // 248;
	final Key F6 = new RedKeyCode("F6", this); // 249;
	final Key F7 = new RedKeyCode("F7", this); // 250;
	final Key F8 = new RedKeyCode("F8", this); // 251;
	final Key F9 = new RedKeyCode("F9", this); // 252;
	final Key F10 = new RedKeyCode("F10", this); // 253;
	final Key F11 = new RedKeyCode("F11", this); // 254;
	final Key F12 = new RedKeyCode("F12", this); // 255;

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

	@Override
	public KeysList listAllKeys() {
		return this.KEYS_LIST;
	}
}
