package com.jhallat.codeviewide.ui.project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jhallat.codeviewide.filesystem.Savable;
import com.jhallat.codeviewide.filesystem.SaveListener;
import com.jhallat.codeviewide.model.ClassModel;

class CoreBuildPath implements BuildPath, Savable {

	private static final long serialVersionUID = -2503727952108586000L;
	private static final Logger log = LoggerFactory.getLogger(CoreBuildPath.class); 
	
	private List<SaveListener> saveListeners = new ArrayList<>();
	private Set<ClassModel> classModels = new TreeSet<>();
	private List<String> loadedJars = new ArrayList<>(); 
	private final String context;
	
	protected CoreBuildPath(String context) {
		this.context = context;
	}

	protected void loadDescriptor(BuildPathDescriptor descriptor) {
		
		for (String jar : descriptor.getLoadedJars()) {
			addJar(new File(jar), false);
		}
		
	}
	
	protected void addClassModel(ClassModel classModel) {
		classModels.add(classModel);
	}
	
	@Override
	public List<String> getJars() {
		return Collections.unmodifiableList(loadedJars);
	}

	@Override
	public Set<ClassModel> getClassModels() {
		return Collections.unmodifiableSet(classModels);
	}

	private String convertToClassName(String path) {
		
		String className = path.substring(0, path.length() - 6);
		return className.replace("/", ".");
		
	}

	private void addJar(File jarFile, boolean save) {
		if (jarFile != null && jarFile.exists()) {
			try(JarFile jar = new JarFile(jarFile);) {
				URL jarURL = new URL("jar:file:" + jarFile.getPath() + "!/");
				URL[] urls = new URL[]{jarURL};
				URLClassLoader classLoader = URLClassLoader.newInstance(urls);
				Enumeration<JarEntry> entries = jar.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					if (entry.getName().endsWith(".class")) {
						Class<?> loadedClass = classLoader.loadClass(convertToClassName(entry.getName()));
						addClassModel(new ClassModel(loadedClass.getName(), loadedClass));
					}
				}
				loadedJars.add(jarFile.getPath());
				if (save) {
					fireSave();
				}
			} catch (IOException e) {
				log.error("Error loading jar", e);
			} catch (ClassNotFoundException e) {
				log.error("Error loading class from jar", e);
			}
		}
		
	}
	
	@Override
	public void addJar(File jarFile) {
		addJar(jarFile, true);
	}

	private void fireSave() {
		
		BuildPathDescriptor descriptor = new BuildPathDescriptor(context, loadedJars);
		for (SaveListener listener : saveListeners) {
			listener.save(descriptor);
		}
		
	}

	@Override
	public void addSaveListener(SaveListener listener) {
		saveListeners.add(listener);
	}
	

}
