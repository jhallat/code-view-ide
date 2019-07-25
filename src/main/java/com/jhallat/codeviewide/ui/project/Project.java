package com.jhallat.codeviewide.ui.project;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jhallat.codeviewide.ui.WorkNode;

import lombok.Data;

@Data
public class Project {

	private boolean isInitialized = false;
	private final String name;
	private final File directory;
	private final List<ProjectAction> projectActions = new ArrayList<>();
	private List<WorkNodeListener> workNodeListeners = new ArrayList<>();
	
	public Project() {
		this.isInitialized = false;
		this.name = null;
		this.directory = null;
	}
	
	public Project(String name, File directory) {
		this.isInitialized = true;
		this.name = name;
		this.directory = directory;
	}
		
	public ProjectAction getRootProjectAction() {
		return new ProjectAction() {

			@Override
			public String getDescription() {
				return "Project: " +  name;
			}
			
		};
	}
	
	public void addWorkNodeListener(WorkNodeListener workNodeListener) {
		this.workNodeListeners.add(workNodeListener);
	}
	
	private void initializeActions() {
		projectActions.add(new PropertiesProjectAction(this));
	}
	
	public List<ProjectAction> getProjectActions() {
		if (projectActions.isEmpty()) {
			initializeActions();
		}
		return Collections.unmodifiableList(projectActions);
	}
	
	public void displayWorkNode(WorkNode workNode) {
		for (WorkNodeListener workNodeListener : workNodeListeners) {
			workNodeListener.workNodeOpened(workNode);
		}
	}
}
