
package com.jfixby.scarabei.red.filesystem.virtual;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;

public class ContentNode {

	final Map<String, ContentNode> nodes = Collections.newMap();
	final Map<String, ContentLeaf> leafs = Collections.newMap();

	public void clearAll () {
		this.leafs.clear();
		this.nodes.clear();
	}

	public ContentNode getChildNode (final String child_name) {
		return this.nodes.get(child_name);
	}

	public ContentLeaf getChildLeaf (final String child_name) {
		return this.leafs.get(child_name);
	}

	public void removeLeaf (final String leaf) {
		this.leafs.remove(leaf);
	}

	public void removeNode (final String node) {
		this.nodes.remove(node);
	}

	public void renameNode (final String node, final String new_name) {
		final ContentNode object = this.nodes.remove(node);
		this.nodes.put(new_name, object);
	}

	public void renameLeaf (final String leaf, final String new_name) {
		final ContentLeaf object = this.leafs.remove(leaf);
		this.leafs.put(new_name, object);
	}

	public List<String> listAllChildren () {
		final List<String> result = Collections.newList();
		result.addAll(this.leafs.keys());
		result.addAll(this.nodes.keys());
		return result;
	}

	public ContentNode createNewNode (final String name) {
		if (this.leafs.containsKey(name) || this.nodes.containsKey(name)) {
			Err.reportError("File already exists: " + name);
		}
		final ContentNode new_node = new ContentNode();
		this.nodes.put(name, new_node);
		return new_node;
	}

	public ContentLeaf createNewFile (final String name) {
		if (this.nodes.containsKey(name)) {
			L.e("Folder with this name already exists: " + name);
			return null;
		}
		ContentLeaf new_file = this.leafs.get(name);
		if (new_file == null) {
			new_file = new ContentLeaf();
			this.leafs.put(name, new_file);
		}
		return new_file;
	}
}
