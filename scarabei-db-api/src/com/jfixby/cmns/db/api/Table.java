
package com.jfixby.cmns.db.api;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.List;

public interface Table {

	List<Entry> listAll () throws IOException;

	TableSchema getSchema () throws IOException;

	Entry newEntry ();

	void addEntry (Entry entry) throws IOException;

	void clear () throws IOException;

	void replaceEntries (List<Entry> batch) throws IOException;

	Collection<Entry> findEntries (String key, String value) throws IOException;

}
