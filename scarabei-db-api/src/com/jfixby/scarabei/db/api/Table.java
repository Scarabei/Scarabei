
package com.jfixby.scarabei.db.api;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.List;

public interface Table {

	List<Entry> listAll () throws IOException;

	TableSchema getSchema () throws IOException;

	Entry newEntry ();

	void addEntry (Entry entry) throws IOException;

	void clear () throws IOException;

	void replaceEntries (List<Entry> batch) throws IOException;

	Collection<Entry> findEntries (TableSchema schema, int indexOf, Object value) throws IOException;

	boolean deleteEntry (final TableSchema schema, final int keyIndex, final Object value) throws IOException;

	boolean deleteEntry (Entry entry) throws IOException;

	void deleteEntries (Collection<Entry> paramEntries) throws IOException;

}
