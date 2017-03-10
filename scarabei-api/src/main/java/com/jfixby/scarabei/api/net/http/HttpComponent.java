
package com.jfixby.scarabei.api.net.http;

public interface HttpComponent {

	HttpURL newURL (String url_string);

	HttpConnection newConnection (HttpURL url);

	HttpConnectionSpecs newConnectionSpecs ();

	HttpConnection newConnection (HttpConnectionSpecs specs);

	HttpCallParams newCallParams ();

	HttpCallExecutor newCallExecutor ();

	HttpCall newCall (HttpCallParams params);

	HttpFileSystemSpecs newHttpFileSystemSpecs ();

	HttpFileSystem newHttpFileSystem (HttpFileSystemSpecs specs);

}
