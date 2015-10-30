package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.path.RelativePath;

public class VirtualFileSystemContent {

	final private ContentNode ROOT;

	public VirtualFileSystemContent() {
		ROOT = new ContentNode();
	}

	public boolean isFile(RelativePath relativePath) {
		if (relativePath.isRoot()) {
			return false;
		}
		ContentLeaf content = this.findLeaf(relativePath);
		return content != null;
	}
	public long lastModified(RelativePath relativePath) {
		ContentLeaf content = this.findLeaf(relativePath);
		return content.lastModified();
	}
	

	public boolean isFolder(RelativePath relativePath) {
		ContentNode content = this.findNode(relativePath);
		return content != null;
	}

	private ContentNode findNode(RelativePath relativePath) {
		if (relativePath.isRoot()) {
			return ROOT;
		}
		List<String> steps = relativePath.steps();
		ContentNode node_cursor = ROOT;
		int steps_cursor = -1;
		while (steps_cursor + 1 < steps.size()) {
			steps_cursor++;
			String next_step_name = steps.getElementAt(steps_cursor);
			node_cursor = node_cursor.getChildNode(next_step_name);
			if (node_cursor == null) {
				return null;
			}
		}
		return node_cursor;
	}

	private ContentLeaf findLeaf(RelativePath relativePath) {
		ContentNode leaf_parent = this.findNode(relativePath.parent());
		if (leaf_parent == null) {
			return null;
		} else {
			String child_name = relativePath.getLastStep();
			ContentLeaf leaf = leaf_parent.getChildLeaf(child_name);
			return leaf;
		}
	}

	public boolean delete(RelativePath relativePath) {
		if (relativePath.isRoot()) {
			this.ROOT.clearAll();
			return false;
		}
		ContentNode leaf_parent = this.findNode(relativePath.parent());
		if (leaf_parent == null) {
			return false;
		} else {
			String child_name = relativePath.getLastStep();
			ContentLeaf leaf = leaf_parent.getChildLeaf(child_name);
			if (leaf != null) {
				leaf_parent.removeLeaf(child_name);
				return true;
			} else {
				ContentNode node = leaf_parent.getChildNode(child_name);
				if (node != null) {
					leaf_parent.removeNode(child_name);
					return true;
				}
			}
		}
		return false;
	}

	public boolean exists(RelativePath relativePath) {
		return this.isFile(relativePath) || this.isFolder(relativePath);
	}

	public boolean mkdirs(RelativePath relativePath) {
		if (relativePath.isRoot()) {
			return true;
		}
		List<String> steps = relativePath.steps();
		ContentNode node_cursor = ROOT;
		ContentNode parent = null;
		int steps_cursor = -1;
		while (steps_cursor + 1 < steps.size()) {
			steps_cursor++;
			String next_step_name = steps.getElementAt(steps_cursor);
			ContentLeaf bad_leaf = node_cursor.getChildLeaf(next_step_name);
			if (bad_leaf != null) {
				return false;
			}
			parent = node_cursor;
			node_cursor = node_cursor.getChildNode(next_step_name);
			if (node_cursor == null) {
				node_cursor = parent.createNewNode(next_step_name);
			}
		}
		return true;
	}

	public void rename(RelativePath relativePath, String new_name) {
		if (relativePath.isRoot()) {
			return;
		}
		ContentNode leaf_parent = this.findNode(relativePath.parent());
		if (leaf_parent == null) {
			return;
		} else {
			String child_name = relativePath.getLastStep();
			ContentLeaf leaf = leaf_parent.getChildLeaf(child_name);
			if (leaf != null) {
				leaf_parent.renameLeaf(child_name, new_name);
			} else {
				ContentNode node = leaf_parent.getChildNode(child_name);
				if (node != null) {
					leaf_parent.renameNode(child_name, new_name);
				}
			}
		}
	}

	public List<String> listChildren(RelativePath relativePath) {
		ContentNode leaf = this.findNode(relativePath);
		if (leaf == null) {
			return null;
		} else {
			return leaf.listAllChildren();
		}
	}

	public ContentLeaf createFile(RelativePath relativePath) {
		ContentNode leaf_parent = this.findNode(relativePath.parent());
		String name = relativePath.steps().getLast();
		return leaf_parent.createNewFile(name);
	}

	public ContentLeaf getContentLeaf(RelativePath relativePath) {
		return this.findLeaf(relativePath);
	}

	
}
