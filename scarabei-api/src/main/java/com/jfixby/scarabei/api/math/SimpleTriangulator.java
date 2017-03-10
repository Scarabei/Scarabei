
package com.jfixby.scarabei.api.math;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.Triangle;

public class SimpleTriangulator {

	static private ComponentInstaller<SimpleTriangulatorComponent> componentInstaller = new ComponentInstaller<SimpleTriangulatorComponent>(
		"SimpleTriangulator");

	public static final void installComponent (SimpleTriangulatorComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}
	
	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final SimpleTriangulatorComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final SimpleTriangulatorComponent component () {
		return componentInstaller.getComponent();
	}

	public static List<Triangle> triangulate (EditableCollection<Float2> vertices_list) {
		return invoke().triangulate(vertices_list);
	}
	
	

}
