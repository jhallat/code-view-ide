package com.jhallat.codeviewide.ui;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.jhallat.codeviewide.model.ClassModel;
import com.jhallat.codeviewide.model.Project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CodeViewIDEUI extends Application {
	
	private ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
	private CoreBuildPath coreBuildPath = CoreBuildPath.getCoreInstance();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		MenuBar menuBar = new MenuBar();
		BorderPane root = new BorderPane();
		
		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New Project");
		newMenuItem.setOnAction(event -> {
			NewProjectDialog newProjectDialog = new NewProjectDialog(primaryStage);
			Optional<Project> project = newProjectDialog.showAndWait();
			if (project.isPresent()) {
				Project newProject = project.get();
				if (newProject.isInitialized()) {
					ProjectPane projectPane = new ProjectPane(newProject, coreBuildPath); //TODO Add buildPath to project
					root.setCenter(projectPane);					
				}
			}
		});
		MenuItem saveMenuItem = new MenuItem("Save Map");
		Menu importMenu = new Menu("Import");
		
		MenuItem importJarMenuItem = new MenuItem(".jar File");
		importJarMenuItem.setOnAction(event -> {
			importJarFile(primaryStage);
		});
		MenuItem importClassFolderMenuItem = new MenuItem("Class Folder");
		importClassFolderMenuItem.setOnAction(event -> {
			importClassFolder(primaryStage);
		});
		importMenu.getItems().addAll(importJarMenuItem, importClassFolderMenuItem);
		
		fileMenu.getItems().addAll(newMenuItem, importMenu, saveMenuItem);
		menuBar.getMenus().addAll(fileMenu);
		

		root.setTop(menuBar);
		
		Scene scene = new Scene(root, 800, 600);
		scene.getStylesheets().add("style.css");
		
		primaryStage.setTitle("Codeview IDE");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private String convertToClassName(String path) {
		
		String className = path.substring(0, path.length() - 6);
		return className.replace("/", ".");
		
	}
	
	public void importJarFile(Stage stage) {
		String lastDirectoryName = applicationProperties.get("last.import.jar.directory");
		File lastDirectory = new File(lastDirectoryName);
		FileChooser fileChooser = new FileChooser();
		if (lastDirectory.exists() && lastDirectory.isDirectory()) {
			fileChooser.setInitialDirectory(lastDirectory);
		}
		fileChooser.getExtensionFilters().addAll(
			new FileChooser.ExtensionFilter(".jar Files", "*.jar")	
		);
		File importFile = fileChooser.showOpenDialog(stage);
		if (importFile != null && importFile.exists()) {
		    applicationProperties.set("last.import.jar.directory", importFile.getParent());
			try(JarFile jarFile = new JarFile(importFile);) {
				URL jarURL = new URL("jar:file:" + importFile.getPath() + "!/");
				URL[] urls = new URL[]{jarURL};
				URLClassLoader classLoader = URLClassLoader.newInstance(urls);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					if (entry.getName().endsWith(".class")) {
						Class<?> loadedClass = classLoader.loadClass(convertToClassName(entry.getName()));
						coreBuildPath.addClassModel(new ClassModel(loadedClass.getName(), loadedClass));
					}
				}
			} catch (IOException e) {
				//TODO replace with logging and user message
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void importClassFolder(Stage stage) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.showDialog(stage);
	}
}
