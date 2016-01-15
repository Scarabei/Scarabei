package com.jfixby.cmns.api.net.http;

public interface HttpComponent {

	HttpURL newURL(String url_string);

	HttpConnection newConnection(HttpURL url);

	HttpConnectionSpecs newConnectionSpecs();

	HttpConnection newConnection(HttpConnectionSpecs specs);

	HttpCallSpecs newCallSpecs();

	HttpCallExecutor newCallExecutor();

	HttpCall newCall(HttpCallSpecs call_scecs);

}
