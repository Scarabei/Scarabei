
package com.jfixby.scarabei.red.name;

import com.jfixby.scarabei.api.assets.AssetsNamespaceComponent;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.NamespaceRegistry;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.util.Utils;

public class DNS implements AssetsNamespaceComponent {

	@Override
	public ID newID (final String asset_id_string) {
		return new DomainName(asset_id_string);
	}

	@Override
	public String SEPARATOR () {
		return ".";
	}

	@Override
	public <T> NamespaceRegistry<T> newRegistry () {
		return new DNSRegistry<T>();
	}

	@Override
	public ID ROOT () {
		return new DomainName();
	}

	@Override
	public ID newID (final Collection<String> list) {
		return new DomainName(Utils.newRelativePath(list));
	}

	@Override
	public boolean isValidString (final String value) {
		return value != null && !"".equals(value) && value.indexOf(' ') == -1;
	}

}
