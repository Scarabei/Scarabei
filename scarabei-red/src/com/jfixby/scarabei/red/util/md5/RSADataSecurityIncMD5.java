
package com.jfixby.scarabei.red.util.md5;

/* see RFC 1321, R. Rivest for further discussion.

 The MD5 Message-Digest Algorithm

 transcribed to Java by Burton Rosenberg,
 Copyright (C) 1996 Burton Rosenberg, ALl rights reserced.

 License to copy and use this software is granted provided
 that this copyright appears intact in the software.
 Software is proved "as is" without express or implied warranty
 of any kind.

 October 26, 1996 1:16 PM.
 October 27, 1996 8:18 PM. (1321 test suite correct)
 October 28, 1996 10:07 AM.
 */

/*
 Copyright (C) 1991-2, RSA Data Security, Inc. Created 1991. All
 rights reserved.

 License to copy and use this software is granted provided that it
 is identified as the "RSA Data Security, Inc. MD5 Message-Digest
 Algorithm" in all material mentioning or referencing this software
 or this function.

 License is also granted to make and use derivative works provided
 that such works are identified as "derived from the RSA Data
 Security, Inc. MD5 Message-Digest Algorithm" in all material
 mentioning or referencing the derived work.

 RSA Data Security, Inc. makes no representations concerning either
 the merchantability of this software or the suitability of this
 software for any particular purpose. It is provided "as is"
 without express or implied warranty of any kind.
 These notices must be retained in any copies of any part of this
 documentation and/or software.
 */

import java.io.IOException;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.md5.MD5Component;
import com.jfixby.scarabei.api.md5.MD5String;
import com.jfixby.scarabei.api.util.JUtils;

public final class RSADataSecurityIncMD5 extends RedMD5 implements MD5Component {

	private int state0, state1, state2, state3;
	private long count;
	private final byte[] block = new byte[64];
	private final int x[] = new int[16];

	private static final byte[] padding = new byte[] {(byte)128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	public RSADataSecurityIncMD5 () {
		this.reset();
	}

	private static final int FF (int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
		a += ((b & c) | ((~b) & d)) + x + ac;
		return ((a << s) | (a >>> (32 - s))) + b;
	}

	private static final int GG (int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
		a += ((b & d) | (c & (~d))) + x + ac;
		return ((a << s) | (a >>> (32 - s))) + b;
	}

	private static final int HH (int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
		a += (b ^ c ^ d) + x + ac;
		return ((a << s) | (a >>> (32 - s))) + b;
	}

	private static final int II (int a, final int b, final int c, final int d, final int x, final int s, final int ac) {
		a += (c ^ (b | (~d))) + x + ac;
		return ((a << s) | (a >>> (32 - s))) + b;
	}

	private static final void encode (final byte[] dst, final int dstoff, final int word) {
		dst[dstoff] = (byte)(word);
		dst[dstoff + 1] = (byte)(word >> 8);
		dst[dstoff + 2] = (byte)(word >> 16);
		dst[dstoff + 3] = (byte)(word >> 24);
	}

	private final void transform (final byte[] src, int pos) {
		int a = this.state0;
		int b = this.state1;
		int c = this.state2;
		int d = this.state3;

		for (int i = 0; i < 16; i++, pos += 4) {
			this.x[i] = (src[pos] & 0xff) | ((src[pos + 1] & 0xff) << 8) | ((src[pos + 2] & 0xff) << 16)
				| ((src[pos + 3] & 0xff) << 24);
		}

		/* Round 1 */

		a = FF(a, b, c, d, this.x[0], 7, 0xd76aa478); /* 1 */
		d = FF(d, a, b, c, this.x[1], 12, 0xe8c7b756); /* 2 */
		c = FF(c, d, a, b, this.x[2], 17, 0x242070db); /* 3 */
		b = FF(b, c, d, a, this.x[3], 22, 0xc1bdceee); /* 4 */
		a = FF(a, b, c, d, this.x[4], 7, 0xf57c0faf); /* 5 */
		d = FF(d, a, b, c, this.x[5], 12, 0x4787c62a); /* 6 */
		c = FF(c, d, a, b, this.x[6], 17, 0xa8304613); /* 7 */
		b = FF(b, c, d, a, this.x[7], 22, 0xfd469501); /* 8 */
		a = FF(a, b, c, d, this.x[8], 7, 0x698098d8); /* 9 */
		d = FF(d, a, b, c, this.x[9], 12, 0x8b44f7af); /* 10 */
		c = FF(c, d, a, b, this.x[10], 17, 0xffff5bb1); /* 11 */
		b = FF(b, c, d, a, this.x[11], 22, 0x895cd7be); /* 12 */
		a = FF(a, b, c, d, this.x[12], 7, 0x6b901122); /* 13 */
		d = FF(d, a, b, c, this.x[13], 12, 0xfd987193); /* 14 */
		c = FF(c, d, a, b, this.x[14], 17, 0xa679438e); /* 15 */
		b = FF(b, c, d, a, this.x[15], 22, 0x49b40821); /* 16 */

		/* Round 2 */
		a = GG(a, b, c, d, this.x[1], 5, 0xf61e2562); /* 17 */
		d = GG(d, a, b, c, this.x[6], 9, 0xc040b340); /* 18 */
		c = GG(c, d, a, b, this.x[11], 14, 0x265e5a51); /* 19 */
		b = GG(b, c, d, a, this.x[0], 20, 0xe9b6c7aa); /* 20 */
		a = GG(a, b, c, d, this.x[5], 5, 0xd62f105d); /* 21 */
		d = GG(d, a, b, c, this.x[10], 9, 0x2441453); /* 22 */
		c = GG(c, d, a, b, this.x[15], 14, 0xd8a1e681); /* 23 */
		b = GG(b, c, d, a, this.x[4], 20, 0xe7d3fbc8); /* 24 */
		a = GG(a, b, c, d, this.x[9], 5, 0x21e1cde6); /* 25 */
		d = GG(d, a, b, c, this.x[14], 9, 0xc33707d6); /* 26 */
		c = GG(c, d, a, b, this.x[3], 14, 0xf4d50d87); /* 27 */
		b = GG(b, c, d, a, this.x[8], 20, 0x455a14ed); /* 28 */
		a = GG(a, b, c, d, this.x[13], 5, 0xa9e3e905); /* 29 */
		d = GG(d, a, b, c, this.x[2], 9, 0xfcefa3f8); /* 30 */
		c = GG(c, d, a, b, this.x[7], 14, 0x676f02d9); /* 31 */
		b = GG(b, c, d, a, this.x[12], 20, 0x8d2a4c8a); /* 32 */

		/* Round 3 */
		a = HH(a, b, c, d, this.x[5], 4, 0xfffa3942); /* 33 */
		d = HH(d, a, b, c, this.x[8], 11, 0x8771f681); /* 34 */
		c = HH(c, d, a, b, this.x[11], 16, 0x6d9d6122); /* 35 */
		b = HH(b, c, d, a, this.x[14], 23, 0xfde5380c); /* 36 */
		a = HH(a, b, c, d, this.x[1], 4, 0xa4beea44); /* 37 */
		d = HH(d, a, b, c, this.x[4], 11, 0x4bdecfa9); /* 38 */
		c = HH(c, d, a, b, this.x[7], 16, 0xf6bb4b60); /* 39 */
		b = HH(b, c, d, a, this.x[10], 23, 0xbebfbc70); /* 40 */
		a = HH(a, b, c, d, this.x[13], 4, 0x289b7ec6); /* 41 */
		d = HH(d, a, b, c, this.x[0], 11, 0xeaa127fa); /* 42 */
		c = HH(c, d, a, b, this.x[3], 16, 0xd4ef3085); /* 43 */
		b = HH(b, c, d, a, this.x[6], 23, 0x4881d05); /* 44 */
		a = HH(a, b, c, d, this.x[9], 4, 0xd9d4d039); /* 45 */
		d = HH(d, a, b, c, this.x[12], 11, 0xe6db99e5); /* 46 */
		c = HH(c, d, a, b, this.x[15], 16, 0x1fa27cf8); /* 47 */
		b = HH(b, c, d, a, this.x[2], 23, 0xc4ac5665); /* 48 */

		/* Round 4 */
		a = II(a, b, c, d, this.x[0], 6, 0xf4292244); /* 49 */
		d = II(d, a, b, c, this.x[7], 10, 0x432aff97); /* 50 */
		c = II(c, d, a, b, this.x[14], 15, 0xab9423a7); /* 51 */
		b = II(b, c, d, a, this.x[5], 21, 0xfc93a039); /* 52 */
		a = II(a, b, c, d, this.x[12], 6, 0x655b59c3); /* 53 */
		d = II(d, a, b, c, this.x[3], 10, 0x8f0ccc92); /* 54 */
		c = II(c, d, a, b, this.x[10], 15, 0xffeff47d); /* 55 */
		b = II(b, c, d, a, this.x[1], 21, 0x85845dd1); /* 56 */
		a = II(a, b, c, d, this.x[8], 6, 0x6fa87e4f); /* 57 */
		d = II(d, a, b, c, this.x[15], 10, 0xfe2ce6e0); /* 58 */
		c = II(c, d, a, b, this.x[6], 15, 0xa3014314); /* 59 */
		b = II(b, c, d, a, this.x[13], 21, 0x4e0811a1); /* 60 */
		a = II(a, b, c, d, this.x[4], 6, 0xf7537e82); /* 61 */
		d = II(d, a, b, c, this.x[11], 10, 0xbd3af235); /* 62 */
		c = II(c, d, a, b, this.x[2], 15, 0x2ad7d2bb); /* 63 */
		b = II(b, c, d, a, this.x[9], 21, 0xeb86d391); /* 64 */

		this.state0 += a;
		this.state1 += b;
		this.state2 += c;
		this.state3 += d;
	}

	public final void reset () {
		this.count = 0;

		this.state0 = 0x67452301;
		this.state1 = 0xefcdab89;
		this.state2 = 0x98badcfe;
		this.state3 = 0x10325476;

		/* Clear traces in memory... */

		for (int i = 0; i < 16; i++) {
			this.x[i] = 0;
		}
	}

	public final void update (final byte b) {
		final int space = 64 - ((int)(this.count & 0x3f));

		this.count++;

		this.block[64 - space] = b;

		if (space == 1) {
			this.transform(this.block, 0);
		}
	}

	public final void update (final byte[] buff, int pos, int len) {
		int space = 64 - ((int)(this.count & 0x3f));

		this.count += len;

		while (len > 0) {
			if (len < space) {
				System.arraycopy(buff, pos, this.block, 64 - space, len);
				break;
			}

			if (space == 64) {
				this.transform(buff, pos);
			} else {
				System.arraycopy(buff, pos, this.block, 64 - space, space);
				this.transform(this.block, 0);
			}

			pos += space;
			len -= space;
			space = 64;
		}
	}

	public final void update (final byte[] b) {
		this.update(b, 0, b.length);
	}

	public final void digest (final byte[] dst, final int pos) {
		final byte[] bits = new byte[8];

		encode(bits, 0, (int)(this.count << 3));
		encode(bits, 4, (int)(this.count >> 29));

		final int idx = (int)this.count & 0x3f;
		final int padLen = (idx < 56) ? (56 - idx) : (120 - idx);

		this.update(padding, 0, padLen);
		this.update(bits, 0, 8);

		encode(dst, pos, this.state0);
		encode(dst, pos + 4, this.state1);
		encode(dst, pos + 8, this.state2);
		encode(dst, pos + 12, this.state3);

		this.reset();
	}

	public final void digest (final byte[] dst) {
		this.digest(dst, 0);
	}

	public final int getDigestLength () {
		return 16;
	}

	public static char[] encodeHex (final byte[] data) {
		return encodeHex(data, true);
	}

	public static char[] encodeHex (final byte[] data, final boolean toLowerCase) {
		return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	/** Used to build output as Hex */
	private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	/** Used to build output as Hex */
	private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

	protected static char[] encodeHex (final byte[] data, final char[] toDigits) {
		final int l = data.length;
		final char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

	@Override
	public MD5String md5Stream (final InputStream input_stream) throws IOException {
		Debug.checkNull("input_stream", input_stream);
		final byte[] data = input_stream.readAll().toArray();
		this.reset();
		this.update(data);
		final byte[] result = new byte[16];
		this.digest(result);
		final String md5_string = encodeHexString(result);
		this.reset();
		return new RedMD5String(md5_string);
	}

	public static String encodeHexString (final byte[] bytes) {
		return JUtils.newString(encodeHex(bytes));
	}

}
