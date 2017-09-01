
package com.jfixby.scarabei.api.db;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.List;

public interface Table {
	Entry newEntry ();

	TableSchema getSchema ();

	Collection<Entry> listAll () throws IOException;

	void addEntry (Entry entry) throws IOException;

	void addEntries (Collection<Entry> batch) throws IOException;

	void clear () throws IOException;

	void replaceEntries (List<Entry> batch) throws IOException;

	Collection<Entry> findEntries (String key, Object value) throws IOException;

	boolean deleteEntry (String key, final Object value) throws IOException;

	boolean deleteEntry (Entry entry) throws IOException;

	void deleteEntries (Collection<Entry> paramEntries) throws IOException;

	String getName ();
}
