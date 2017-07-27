
package com.jfixby.scarabei.red.filesystem.virtual;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class InMemoryFileSystemContent {

	final private ContentNode ROOT;

	public InMemoryFileSystemContent () {
		this.ROOT = new ContentNode();
	}

	public boolean isFile (final RelativePath relativePath) {
		if (relativePath.isRoot()) {
			return false;
		}
		final ContentLeaf content = this.findLeaf(relativePath);
		return content != null;
	}

	public long lastModified (final RelativePath relativePath) {
		final ContentLeaf content = this.findLeaf(relativePath);
		return content.lastModified();
	}

	public boolean isFolder (final RelativePath relativePath) {
		final ContentNode content = this.findNode(relativePath);
		return content != null;
	}

	private ContentNode findNode (final RelativePath relativePath) {
		if (relativePath.isRoot()) {
			return this.ROOT;
		}
		final Collection<String> steps = relativePath.steps();
		ContentNode node_cursor = this.ROOT;
		int steps_cursor = -1;
		while (steps_cursor + 1 < steps.size()) {
			steps_cursor++;
			final String next_step_name = steps.getElementAt(steps_cursor);
			node_cursor = node_cursor.getChildNode(next_step_name);
			if (node_cursor == null) {
				return null;
			}
		}
		return node_cursor;
	}

	private ContentLeaf findLeaf (final RelativePath relativePath) {
		final ContentNode leaf_parent = this.findNode(relativePath.parent());
		if (leaf_parent == null) {
			return null;
		} else {
			final String child_name = relativePath.getLastStep();
			final ContentLeaf leaf = leaf_parent.getChildLeaf(child_name);
			return leaf;
		}
	}

	public boolean delete (final RelativePath relativePath) {
		if (relativePath.isRoot()) {
			this.ROOT.clearAll();
			return false;
		}
		final ContentNode leaf_parent = this.findNode(relativePath.parent());
		if (leaf_parent == null) {
			return false;
		} else {
			final String child_name = relativePath.getLastStep();
			final ContentLeaf leaf = leaf_parent.getChildLeaf(child_name);
			if (leaf != null) {
				leaf_parent.removeLeaf(child_name);
				return true;
			} else {
				final ContentNode node = leaf_parent.getChildNode(child_name);
				if (node != null) {
					leaf_parent.removeNode(child_name);
					return true;
				}
			}
		}
		return false;
	}

	public boolean exists (final RelativePath relativePath) {
		return this.isFile(relativePath) || this.isFolder(relativePath);
	}

	public boolean mkdirs (final RelativePath relativePath) {
		if (relativePath.isRoot()) {
			return true;
		}
		final Collection<String> steps = relativePath.steps();
		ContentNode node_cursor = this.ROOT;
		ContentNode parent = null;
		int steps_cursor = -1;
		while (steps_cursor + 1 < steps.size()) {
			steps_cursor++;
			final String next_step_name = steps.getElementAt(steps_cursor);
			final ContentLeaf bad_leaf = node_cursor.getChildLeaf(next_step_name);
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

	public void rename (final RelativePath relativePath, final String new_name) {
		if (relativePath.isRoot()) {
			return;
		}
		final ContentNode leaf_parent = this.findNode(relativePath.parent());
		if (leaf_parent == null) {
			return;
		} else {
			final String child_name = relativePath.getLastStep();
			final ContentLeaf leaf = leaf_parent.getChildLeaf(child_name);
			if (leaf != null) {
				leaf_parent.renameLeaf(child_name, new_name);
			} else {
				final ContentNode node = leaf_parent.getChildNode(child_name);
				if (node != null) {
					leaf_parent.renameNode(child_name, new_name);
				}
			}
		}
	}

	public List<String> listChildren (final RelativePath relativePath) {
		final ContentNode leaf = this.findNode(relativePath);
		if (leaf == null) {
			return null;
		} else {
			return leaf.listAllChildren();
		}
	}

	public ContentLeaf createFile (final RelativePath relativePath) {
		final ContentNode leaf_parent = this.findNode(relativePath.parent());
		final String name = relativePath.steps().getLast();
		return leaf_parent.createNewFile(name);
	}

	public ContentLeaf getContentLeaf (final RelativePath relativePath) {
		return this.findLeaf(relativePath);
	}

}
