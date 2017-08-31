
package com.jfixby.scarabei.api.db;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.List;

public interface Table {
	Entry newEntry ();

	TableSchema getSchema () throws IOException;

	Collection<Entry> listAll () throws IOException;

	void addEntry (Entry entry) throws IOException;

	void addEntries (Collection<Entry> batch) throws IOException;

	void clear () throws IOException;

	void replaceEntries (List<Entry> batch) throws IOException;

	Collection<Entry> findEntries (TableSchema schema, int indexOf, Object value) throws IOException;

	boolean deleteEntry (final TableSchema schema, final int keyIndex, final Object value) throws IOException;

	boolean deleteEntry (Entry entry) throws IOException;

	void deleteEntries (Collection<Entry> paramEntries) throws IOException;

}
