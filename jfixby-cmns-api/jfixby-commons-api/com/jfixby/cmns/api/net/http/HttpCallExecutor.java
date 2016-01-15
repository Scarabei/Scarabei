package com.jfixby.cmns.api.net.http;

import java.io.IOException;

public interface HttpCallExecutor {

	HttpCallProgress execute(HttpCall call) throws IOException;

}
