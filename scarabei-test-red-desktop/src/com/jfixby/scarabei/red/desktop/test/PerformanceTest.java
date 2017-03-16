
package com.jfixby.scarabei.red.desktop.test;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTest {

	private static final int KKK = 10000;

	private static long current_time;
	static byte[] b = new byte[1024 * 1024 * 500000000];

	public static void main (String[] args) {
		// DesktopAssembler.setup();

		check(b);
		b = null;

		for (;;) {
			runTestA();
			runTestB();

		}
	}

	private static void runTestA () {
		log("BeginTest");
		for (int t = 0; t < TESTS; t++) {
			setupList();
			begin_time = System.currentTimeMillis();
			testA();
			end_time = System.currentTimeMillis();
			disposeList();
			printResult("for (final Object o : list) " + t, end_time, begin_time);
		}
		log("EndTest");
	}

	static final int TESTS = 10;

	private static void runTestB () {
		log("BeginTest");
		for (int t = 0; t < TESTS; t++) {
			setupList();
			begin_time = System.currentTimeMillis();
			testB();
			end_time = System.currentTimeMillis();
			disposeList();
			printResult("for (int i = 0; i < list.size(); i++) " + t, end_time, begin_time);
		}
		log("EndTest");
	}

	private static void disposeList () {
		list = null;
		value = null;
	}

	static int D = 1024 * 1024;
	static int N = D * 1;

	private static Object value;

	private static void setupList () {

		list = new ArrayList<Object>();
		for (int i = 0; i < N; i++) {
			list.add("4");
			if (i % D == 0) {
				// log("" + i / D, N / D);
			}
		}
	}

	private static void testA () {
		for (int k = 0; k < KKK; k++)
			for (final Object o : list) {
				check(o);
			}
	}

	private static void check (final Object o) {
		value = o;
	}

	private static void testB () {
		for (int k = 0; k < KKK; k++) {
			int i = 0;
			final int size = list.size();
			for (i = 0; i < size; i++) {
				final Object o = list.get(i);
				check(o);
			}
		}
	}

	static List<Object> list = null;

	static long begin_time = 0;
	static long end_time = 0;
	private static long diff;

	private static void printResult (String test_tag, final long end_time, final long begin_time) {
		diff = end_time - begin_time;
		log(test_tag, diff);
	}

	private static void log (String test_tag, Object message) {
		log(test_tag + " > " + message);
	}

	private static void log (Object message) {
		System.out.println("" + message);
	}

}
