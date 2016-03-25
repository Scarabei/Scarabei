package com.jfixby.red.desktop.test;

import org.junit.Test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.BitForm;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.desktop.DesktopAssembler;

public class BitsTest {

    @Test
    public void test() {
	DesktopAssembler.setup();
	for (int i = 0; i < 32 + 0 * 256 * 256; i++) {
	    BitForm bitform = JUtils.bitformOf(i, 4);
	    L.d(i + "", bitform + " vs " + Integer.toBinaryString(i));
	}

    }

}
