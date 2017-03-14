
package com.jfixby.scarabei.adopted.gdx;

import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.Triangle;
import com.jfixby.scarabei.api.math.SimpleTriangulatorComponent;

public class GdxSimpleTriangulator implements SimpleTriangulatorComponent {

	@Override
	public List<Triangle> triangulate (final EditableCollection<Float2> vertices_list) {
		final FloatArray vertices = new FloatArray();
		for (int i = 0; i < vertices_list.size(); i++) {
			final Float2 point_i = vertices_list.getElementAt(i);
			vertices.add((float)point_i.getX());
			vertices.add((float)point_i.getY());
		}

		final EarClippingTriangulator triangulator = new EarClippingTriangulator();
		final ShortArray result_numeration = triangulator.computeTriangles(vertices);
		final List<Short> indexes = Collections.newList();
		for (int i = 0; i < result_numeration.size; i++) {
			final Short element = result_numeration.get(i);
			indexes.add(element);
		}
		final List<Triangle> result = Collections.newList();
		for (int i = 0; i < indexes.size(); i = i + 3) {
			final int index_0 = indexes.getElementAt(i);
			final int index_1 = indexes.getElementAt(i + 1);
			final int index_2 = indexes.getElementAt(i + 2);

			final double x_0 = vertices.get(index_0 * 2);
			final double y_0 = vertices.get(index_0 * 2 + 1);

			final double x_1 = vertices.get(index_1 * 2);
			final double y_1 = vertices.get(index_1 * 2 + 1);

			final double x_2 = vertices.get(index_2 * 2);
			final double y_2 = vertices.get(index_2 * 2 + 1);

			final Triangle triangle = Geometry.newTriangle();

			triangle.A().relative().setXY(x_0, y_0);
			triangle.B().relative().setXY(x_1, y_1);
			triangle.C().relative().setXY(x_2, y_2);
			result.add(triangle);

		}

		return result;
	}

}
