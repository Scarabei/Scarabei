package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.log.L;

public class ContentNode {

	final Map<String, ContentNode> nodes = JUtils.newMap();
	final Map<String, ContentLeaf> leafs = JUtils.newMap();

	public void clearAll() {
		leafs.clear();
		nodes.clear();
	}

	public ContentNode getChildNode(String child_name) {
		return nodes.get(child_name);
	}

	public ContentLeaf getChildLeaf(String child_name) {
		return leafs.get(child_name);
	}

	public void removeLeaf(String leaf) {
		leafs.remove(leaf);
	}

	public void removeNode(String node) {
		nodes.remove(node);
	}

	public void renameNode(String node, String new_name) {
		ContentNode object = nodes.remove(node);
		nodes.put(new_name, object);
	}

	public void renameLeaf(String leaf, String new_name) {
		ContentLeaf object = leafs.remove(leaf);
		leafs.put(new_name, object);
	}

	public List<String> listAllChildren() {
		final List<String> result = JUtils.newList();
		result.addAll(this.leafs.keys());
		result.addAll(this.nodes.keys());
		return result;
	}

	public ContentNode createNewNode(String name) {
		if (this.leafs.containsKey(name) || this.nodes.containsKey(name)) {
			throw new Error("File already exists: " + name);
		}
		ContentNode new_node = new ContentNode();
		this.nodes.put(name, new_node);
		return new_node;
	}

	public ContentLeaf createNewFile(String name) {
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
