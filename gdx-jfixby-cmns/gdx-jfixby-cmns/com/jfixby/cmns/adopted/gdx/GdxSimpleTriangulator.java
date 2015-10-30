package com.jfixby.cmns.adopted.gdx;

import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ShortArray;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Triangle;
import com.jfixby.cmns.api.math.SimpleTriangulatorComponent;

public class GdxSimpleTriangulator implements SimpleTriangulatorComponent {

	@Override
	public List<Triangle> triangulate(EditableCollection<Float2> vertices_list) {
		FloatArray vertices = new FloatArray();
		for (int i = 0; i < vertices_list.size(); i++) {
			Float2 point_i = vertices_list.getElementAt(i);
			vertices.add((float) point_i.getX());
			vertices.add((float) point_i.getY());
		}

		EarClippingTriangulator triangulator = new EarClippingTriangulator();
		ShortArray result_numeration = triangulator.computeTriangles(vertices);
		List<Short> indexes = JUtils.newList();
		for (int i = 0; i < result_numeration.size; i++) {
			Short element = result_numeration.get(i);
			indexes.add(element);
		}
		List<Triangle> result = JUtils.newList();
		for (int i = 0; i < indexes.size(); i = i + 3) {
			int index_0 = indexes.getElementAt(i);
			int index_1 = indexes.getElementAt(i + 1);
			int index_2 = indexes.getElementAt(i + 2);

			double x_0 = vertices.get(index_0 * 2);
			double y_0 = vertices.get(index_0 * 2 + 1);

			double x_1 = vertices.get(index_1 * 2);
			double y_1 = vertices.get(index_1 * 2 + 1);

			double x_2 = vertices.get(index_2 * 2);
			double y_2 = vertices.get(index_2 * 2 + 1);

			Triangle triangle = Geometry.newTriangle();

			triangle.A().relative().setXY(x_0, y_0);
			triangle.B().relative().setXY(x_1, y_1);
			triangle.C().relative().setXY(x_2, y_2);
			result.add(triangle);

		}

		return result;
	}

}
