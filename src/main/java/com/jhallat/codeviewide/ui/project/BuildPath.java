package com.jhallat.codeviewide.ui.project;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.jhallat.codeviewide.model.ClassModel;

public interface BuildPath extends Serializable {

	void addJar(File jarFile);
	List<String> getJars();
	Set<ClassModel> getClassModels();
	
}
