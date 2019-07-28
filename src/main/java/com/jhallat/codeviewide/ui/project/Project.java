package com.jhallat.codeviewide.ui.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jhallat.codeviewide.filesystem.Descriptor;
import com.jhallat.codeviewide.filesystem.FileSystem;
import com.jhallat.codeviewide.filesystem.SaveListener;
import com.jhallat.codeviewide.ui.CodeViewProperties;
import com.jhallat.codeviewide.ui.WorkNode;

import javafx.stage.Stage;
import lombok.Data;

@Data
public class Project implements SaveListener {

	private boolean isInitialized = false;
	private final ProjectDescriptor projectDescriptor;
	private final List<ProjectAction> projectActions = new ArrayList<>();
	private List<WorkNodeListener> workNodeListeners = new ArrayList<>();
	private final CoreBuildPath buildPath;
	private final Stage stage;
	private final CodeViewProperties properties;
	private final FileSystem fileSystem;

	
	public Project(ProjectDescriptor projectDescriptor, FileSystem fileSystem, CodeViewProperties properties, Stage stage) {
		this.isInitialized = true;
		this.projectDescriptor = projectDescriptor;
		this.properties = properties;
		this.stage = stage;
		this.buildPath = new CoreBuildPath(projectDescriptor.getContext());
		this.buildPath.addSaveListener(this);
		this.fileSystem = fileSystem;
	}

	public ProjectDescriptor getDescriptor() {
		return this.projectDescriptor;
	}
	
	public ProjectAction getRootProjectAction() {
		return new ProjectAction() {

			@Override
			public String getDescription() {
				return "Project: " +  projectDescriptor.getName();
			}
			
		};
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public void addWorkNodeListener(WorkNodeListener workNodeListener) {
		this.workNodeListeners.add(workNodeListener);
	}
	
	private void initializeActions() {
		projectActions.add(new PropertiesProjectAction(properties, this));
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

	@Override
	public void save(Descriptor descriptor) {
		fileSystem.save(descriptor);
	}

	public void loadContents() {
		BuildPathDescriptor buildPathDescriptor = fileSystem.open(BuildPathDescriptor.BUILD_PATH_IDENTIFIER);
		buildPath.loadDescriptor(buildPathDescriptor);
	}


}
