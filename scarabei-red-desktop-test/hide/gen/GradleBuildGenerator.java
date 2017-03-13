
package com.jfixby.scarabei.examples.gradle.gen;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.tool.eclipse.dep.EclipseProjectDependencies;
import com.jfixby.tool.eclipse.dep.EclipseProjectInfo;
import com.jfixby.tool.eclipse.dep.EclipseWorkSpaceSettings;

public class GradleBuildGenerator {

	public static final String JAVA_VERSION = "#JAVA_VERSION#";
	public static final String SRC_DIRS = "#SRC_DIRS#";
	public static final String COMPILE_PROJECTS = "#COMPILE_PROJECTS#";

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		final File workspace_folder = LocalFileSystem.newFile("D:\\[DEV]\\[CODE]\\[WS-20]");
		final EclipseWorkSpaceSettings workspace_settings = EclipseWorkSpaceSettings.readWorkspaceSettings(workspace_folder);

		Collection<String> projects = workspace_settings.listProjects();
		final String template = LocalFileSystem.ApplicationHome().child("gradle_template.txt").readToString();

		projects = projects.filter(project -> {
			final EclipseProjectInfo info = workspace_settings.getProjectInfo(project);
			final File location = info.getProjectPath();
			final File gradleConfig = location.child("build.gradle");
			final EclipseProjectDependencies deps = info.getDependencies();
			final Collection<String> src = deps.getSourceFoldersList();
			if (src.size() == 0) {
				return false;
			}
			if (project.startsWith("gdx")) {
				return false;
			}
			try {
				return !gradleConfig.exists();
			} catch (final IOException e) {
				e.printStackTrace();
				return false;
			}
		});
		projects.print("projects");
		final int i = 0;
		for (final String project : projects) {
			final EclipseProjectInfo info = workspace_settings.getProjectInfo(project);
			final File location = info.getProjectPath();
			final File gradleConfig = location.child("build.gradle");
			final File classPath = location.child(".classpath");
			if (gradleConfig.exists()) {
// L.d("gradle config found", gradleConfig);
// L.d(gradleConfig.readToString());
			} else {

				L.d("---missing gradle config", location + " -------------------------------------------");

// final String cpthData = classPath.readToString();

				final EclipseProjectDependencies deps = info.getDependencies();
				final Collection<String> sources = deps.getSourceFoldersList();
				final Collection<String> depProjects = deps.getProjectsList();

				String proposal = template;
				proposal = proposal.replaceAll(JAVA_VERSION, "1.8");
				proposal = proposal.replaceAll(COMPILE_PROJECTS, compileProjects(depProjects));
				proposal = proposal.replaceAll(SRC_DIRS, srcDirs(sources));
// L.d("proposed config");
				L.d(proposal);
////
// if (i == 1) {
// Sys.exit();
// }
// i++;
				gradleConfig.writeString(proposal);

			}
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
