
package com.jfixby.scarabei.examples.gradle.gen;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.tool.eclipse.dep.EclipseProjectInfo;
import com.jfixby.tool.eclipse.dep.EclipseWorkSpaceSettings;

public class FixDeps {

	public static final String JAVA_VERSION = "#JAVA_VERSION#";
	public static final String SRC_DIRS = "#SRC_DIRS#";
	public static final String COMPILE_PROJECTS = "#COMPILE_PROJECTS#";

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		final File workspace_folder = LocalFileSystem.newFile("D:\\[DEV]\\[CODE]\\[WS-20]");
		final EclipseWorkSpaceSettings workspace_settings = EclipseWorkSpaceSettings.readWorkspaceSettings(workspace_folder);

		Collection<String> projects = workspace_settings.listProjects();
		final String template = LocalFileSystem.ApplicationHome().child("gradle_template.txt").readToString();
		final Map<String, String> replace = Collections.newMap();

		replace.put("red-triplane-ui-api", "red-triplane-api-ui");
		replace.put("red-triplane-box2d-api", "red-triplane-api-box2d");
		replace.put("red-triplane-pizza-api", "red-triplane-api-pizza");
		replace.put("red-triplane-fonts-api", "red-triplane-api-fonts");
		replace.put("red-triplane-fokker-api", "red-triplane-api-fokker");
		replace.put("red-triplane-scene2d-api", "red-triplane-api-scene2d");
		replace.put("red-triplane-shader-api", "red-triplane-api-shader");

		projects = projects.filter(project -> {
			final EclipseProjectInfo info = workspace_settings.getProjectInfo(project);
			final File location = info.getProjectPath();
			final File classPath = location.child(".classpath");

			try {
				if (!classPath.exists()) {
					return false;
				}
				final String data = classPath.readToString();
				for (final String key : replace.keys()) {
					if (data.contains(key)) {
						return true;
					}
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
			return false;

		});

		projects.print("projects");
		final int i = 0;
		for (final String project : projects) {
			final EclipseProjectInfo info = workspace_settings.getProjectInfo(project);
			final File location = info.getProjectPath();
			final File classPath = location.child(".classpath");
			final String data = classPath.readToString();
			String newData = data;
			for (final String key : replace.keys()) {
				newData = newData.replaceAll(key, replace.get(key));
			}
			if (!newData.equals(data)) {
				L.d("needs fix", classPath);
// L.d("original", data);
// L.d(" new", newData);
			}

			classPath.writeString(newData);
		}

	}

	private static String srcDirs (final Collection<String> depProjects) {
		return JUtils.wrapSequence(i -> "'" + depProjects.getElementAt(i) + "/'", depProjects.size(), "[", "]", ",");

	}

	private static String compileProjects (final Collection<String> depProjects) {
		return JUtils.wrapSequence(i -> " compile project(\":" + depProjects.getElementAt(i) + "\")", depProjects.size(), "", "",
			"\n");
	}

}
