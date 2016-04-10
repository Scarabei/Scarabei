package com.jfixby.cmns.api.net.http;

public interface HttpComponent {

	HttpURL newURL(String url_string);

	HttpConnection newConnection(HttpURL url);

	HttpConnectionSpecs newConnectionSpecs();

	HttpConnection newConnection(HttpConnectionSpecs specs);

	HttpCallParams newCallParams();

	HttpCallExecutor newCallExecutor();

	HttpCall newCall(HttpCallParams params);

}
