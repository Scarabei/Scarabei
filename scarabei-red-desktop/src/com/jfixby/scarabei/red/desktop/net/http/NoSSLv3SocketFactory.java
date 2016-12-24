
package com.jfixby.scarabei.red.desktop.net.http;

/*Copyright 2015 Bhavit Singh Sengar
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class NoSSLv3SocketFactory extends SSLSocketFactory {
	private final SSLSocketFactory delegate;

	public NoSSLv3SocketFactory () {
		this.delegate = HttpsURLConnection.getDefaultSSLSocketFactory();
	}

	public NoSSLv3SocketFactory (final SSLSocketFactory delegate) {
		this.delegate = delegate;
	}

	@Override
	public String[] getDefaultCipherSuites () {
		return this.delegate.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites () {
		return this.delegate.getSupportedCipherSuites();
	}

	private Socket makeSocketSafe (Socket socket) {
		if (socket instanceof SSLSocket) {
			socket = new NoSSLv3SSLSocket((SSLSocket)socket);
		}
		return socket;
	}

	@Override
	public Socket createSocket (final Socket s, final String host, final int port, final boolean autoClose) throws IOException {
		return this.makeSocketSafe(this.delegate.createSocket(s, host, port, autoClose));
	}

	@Override
	public Socket createSocket (final String host, final int port) throws IOException {
		return this.makeSocketSafe(this.delegate.createSocket(host, port));
	}

	@Override
	public Socket createSocket (final String host, final int port, final InetAddress localHost, final int localPort)
		throws IOException {
		return this.makeSocketSafe(this.delegate.createSocket(host, port, localHost, localPort));
	}

	@Override
	public Socket createSocket (final InetAddress host, final int port) throws IOException {
		return this.makeSocketSafe(this.delegate.createSocket(host, port));
	}

	@Override
	public Socket createSocket (final InetAddress address, final int port, final InetAddress localAddress, final int localPort)
		throws IOException {
		return this.makeSocketSafe(this.delegate.createSocket(address, port, localAddress, localPort));
	}

	private class NoSSLv3SSLSocket extends DelegateSSLSocket {

		private NoSSLv3SSLSocket (final SSLSocket delegate) {
			super(delegate);

		}

		@Override
		public void setEnabledProtocols (String[] protocols) {
			if (protocols != null && protocols.length == 1 && "SSLv3".equals(protocols[0])) {

				final List<String> enabledProtocols = new ArrayList<String>(Arrays.asList(this.delegate.getEnabledProtocols()));
				if (enabledProtocols.size() > 1) {
					enabledProtocols.remove("SSLv3");
					System.out.println("Removed SSLv3 from enabled protocols");
				} else {
					System.out.println("SSL stuck with protocol available for " + String.valueOf(enabledProtocols));
				}
				protocols = enabledProtocols.toArray(new String[enabledProtocols.size()]);
			}

			super.setEnabledProtocols(protocols);
		}
	}

	public class DelegateSSLSocket extends SSLSocket {

		protected final SSLSocket delegate;

		DelegateSSLSocket (final SSLSocket delegate) {
			this.delegate = delegate;
		}

		@Override
		public String[] getSupportedCipherSuites () {
			return this.delegate.getSupportedCipherSuites();
		}

		@Override
		public String[] getEnabledCipherSuites () {
			return this.delegate.getEnabledCipherSuites();
		}

		@Override
		public void setEnabledCipherSuites (final String[] suites) {
			this.delegate.setEnabledCipherSuites(suites);
		}

		@Override
		public String[] getSupportedProtocols () {
			return this.delegate.getSupportedProtocols();
		}

		@Override
		public String[] getEnabledProtocols () {
			return this.delegate.getEnabledProtocols();
		}

		@Override
		public void setEnabledProtocols (final String[] protocols) {
			this.delegate.setEnabledProtocols(protocols);
		}

		@Override
		public SSLSession getSession () {
			return this.delegate.getSession();
		}

		@Override
		public void addHandshakeCompletedListener (final HandshakeCompletedListener listener) {
			this.delegate.addHandshakeCompletedListener(listener);
		}

		@Override
		public void removeHandshakeCompletedListener (final HandshakeCompletedListener listener) {
			this.delegate.removeHandshakeCompletedListener(listener);
		}

		@Override
		public void startHandshake () throws IOException {
			this.delegate.startHandshake();
		}

		@Override
		public void setUseClientMode (final boolean mode) {
			this.delegate.setUseClientMode(mode);
		}

		@Override
		public boolean getUseClientMode () {
			return this.delegate.getUseClientMode();
		}

		@Override
		public void setNeedClientAuth (final boolean need) {
			this.delegate.setNeedClientAuth(need);
		}

		@Override
		public void setWantClientAuth (final boolean want) {
			this.delegate.setWantClientAuth(want);
		}

		@Override
		public boolean getNeedClientAuth () {
			return this.delegate.getNeedClientAuth();
		}

		@Override
		public boolean getWantClientAuth () {
			return this.delegate.getWantClientAuth();
		}

		@Override
		public void setEnableSessionCreation (final boolean flag) {
			this.delegate.setEnableSessionCreation(flag);
		}

		@Override
		public boolean getEnableSessionCreation () {
			return this.delegate.getEnableSessionCreation();
		}

		@Override
		public void bind (final SocketAddress localAddr) throws IOException {
			this.delegate.bind(localAddr);
		}

		@Override
		public synchronized void close () throws IOException {
			this.delegate.close();
		}

		@Override
		public void connect (final SocketAddress remoteAddr) throws IOException {
			this.delegate.connect(remoteAddr);
		}

		@Override
		public void connect (final SocketAddress remoteAddr, final int timeout) throws IOException {
			this.delegate.connect(remoteAddr, timeout);
		}

		@Override
		public SocketChannel getChannel () {
			return this.delegate.getChannel();
		}

		@Override
		public InetAddress getInetAddress () {
			return this.delegate.getInetAddress();
		}

		@Override
		public InputStream getInputStream () throws IOException {
			return this.delegate.getInputStream();
		}

		@Override
		public boolean getKeepAlive () throws SocketException {
			return this.delegate.getKeepAlive();
		}

		@Override
		public InetAddress getLocalAddress () {
			return this.delegate.getLocalAddress();
		}

		@Override
		public int getLocalPort () {
			return this.delegate.getLocalPort();
		}

		@Override
		public SocketAddress getLocalSocketAddress () {
			return this.delegate.getLocalSocketAddress();
		}

		@Override
		public boolean getOOBInline () throws SocketException {
			return this.delegate.getOOBInline();
		}

		@Override
		public OutputStream getOutputStream () throws IOException {
			return this.delegate.getOutputStream();
		}

		@Override
		public int getPort () {
			return this.delegate.getPort();
		}

		@Override
		public synchronized int getReceiveBufferSize () throws SocketException {
			return this.delegate.getReceiveBufferSize();
		}

		@Override
		public SocketAddress getRemoteSocketAddress () {
			return this.delegate.getRemoteSocketAddress();
		}

		@Override
		public boolean getReuseAddress () throws SocketException {
			return this.delegate.getReuseAddress();
		}

		@Override
		public synchronized int getSendBufferSize () throws SocketException {
			return this.delegate.getSendBufferSize();
		}

		@Override
		public int getSoLinger () throws SocketException {
			return this.delegate.getSoLinger();
		}

		@Override
		public synchronized int getSoTimeout () throws SocketException {
			return this.delegate.getSoTimeout();
		}

		@Override
		public boolean getTcpNoDelay () throws SocketException {
			return this.delegate.getTcpNoDelay();
		}

		@Override
		public int getTrafficClass () throws SocketException {
			return this.delegate.getTrafficClass();
		}

		@Override
		public boolean isBound () {
			return this.delegate.isBound();
		}

		@Override
		public boolean isClosed () {
			return this.delegate.isClosed();
		}

		@Override
		public boolean isConnected () {
			return this.delegate.isConnected();
		}

		@Override
		public boolean isInputShutdown () {
			return this.delegate.isInputShutdown();
		}

		@Override
		public boolean isOutputShutdown () {
			return this.delegate.isOutputShutdown();
		}

		@Override
		public void sendUrgentData (final int value) throws IOException {
			this.delegate.sendUrgentData(value);
		}

		@Override
		public void setKeepAlive (final boolean keepAlive) throws SocketException {
			this.delegate.setKeepAlive(keepAlive);
		}

		@Override
		public void setOOBInline (final boolean oobinline) throws SocketException {
			this.delegate.setOOBInline(oobinline);
		}

		@Override
		public void setPerformancePreferences (final int connectionTime, final int latency, final int bandwidth) {
			this.delegate.setPerformancePreferences(connectionTime, latency, bandwidth);
		}

		@Override
		public synchronized void setReceiveBufferSize (final int size) throws SocketException {
			this.delegate.setReceiveBufferSize(size);
		}

		@Override
		public void setReuseAddress (final boolean reuse) throws SocketException {
			this.delegate.setReuseAddress(reuse);
		}

		@Override
		public synchronized void setSendBufferSize (final int size) throws SocketException {
			this.delegate.setSendBufferSize(size);
		}

		@Override
		public void setSoLinger (final boolean on, final int timeout) throws SocketException {
			this.delegate.setSoLinger(on, timeout);
		}

		@Override
		public synchronized void setSoTimeout (final int timeout) throws SocketException {
			this.delegate.setSoTimeout(timeout);
		}

		@Override
		public void setTcpNoDelay (final boolean on) throws SocketException {
			this.delegate.setTcpNoDelay(on);
		}

		@Override
		public void setTrafficClass (final int value) throws SocketException {
			this.delegate.setTrafficClass(value);
		}

		@Override
		public void shutdownInput () throws IOException {
			this.delegate.shutdownInput();
		}

		@Override
		public void shutdownOutput () throws IOException {
			this.delegate.shutdownOutput();
		}

		@Override
		public String toString () {
			return this.delegate.toString();
		}

		@Override
		public boolean equals (final Object o) {
			return this.delegate.equals(o);
		}
	}
}
