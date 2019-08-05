package com.jhallat.codeviewide.ui;

import java.util.Optional;

import com.jhallat.codeviewide.filesystem.FileSystem;
import com.jhallat.codeviewide.ui.message.HotKeyMessage;
import com.jhallat.codeviewide.ui.message.HotKeyMessage.HotKey;
import com.jhallat.codeviewide.ui.message.HotKeyMessageEvent;
import com.jhallat.codeviewide.ui.message.MessageEventBus;
import com.jhallat.codeviewide.ui.project.NewProjectDialog;
import com.jhallat.codeviewide.ui.project.Project;
import com.jhallat.codeviewide.ui.project.ProjectDescriptor;
import com.jhallat.codeviewide.ui.project.ProjectModel;
import com.jhallat.codeviewide.ui.project.ProjectPane;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class CodeViewIDEUI extends Application {

	private CodeViewProperties properties = CodeViewProperties.getInstance();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		MenuBar menuBar = new MenuBar();
		BorderPane root = new BorderPane();
		MessageEventBus eventBus = new MessageEventBus();

		Menu fileMenu = new Menu("File");
		MenuItem newMenuItem = new MenuItem("New Project");
		newMenuItem.getStyleClass().add("menu-item");
		newMenuItem.setOnAction(event -> {
			NewProjectDialog newProjectDialog = new NewProjectDialog(primaryStage, properties);
			Optional<ProjectModel> projectOption = newProjectDialog.showAndWait();
			// TODO need to check if project already exists. Handled in dialog???
			if (projectOption.isPresent()) {
				ProjectModel newProjectModel = projectOption.get();
				if (!newProjectModel.isCancelled()) { // TODO This should be handled with proper Optional return from
														// dialog
					ProjectDescriptor projectDescriptor = new ProjectDescriptor(newProjectModel.getName(),
							newProjectModel.getDirectory());
					FileSystem fileSystem = new FileSystem(
							projectDescriptor.getDirectory() + "/" + projectDescriptor.getName());
					fileSystem.save(projectDescriptor);
					Project project = new Project(projectDescriptor, fileSystem, properties, primaryStage, eventBus);
					properties.set(CodeViewProperties.LAST_PROJECT_DIRECTORY,
							projectDescriptor.getDirectory() + "/" + projectDescriptor.getName());

					ProjectPane projectPane = new ProjectPane(project);
					root.setCenter(projectPane);
				}
			}
		});

		MenuItem importClassFolderMenuItem = new MenuItem("Class Folder");
		importClassFolderMenuItem.setOnAction(event -> {
			importClassFolder(primaryStage);
		});

		fileMenu.getItems().addAll(newMenuItem);
		menuBar.getMenus().addAll(fileMenu);

		root.setTop(menuBar);

		Scene scene = new Scene(root, 1200, 800);
		scene.getStylesheets().add("style.css");
		scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				final KeyCombination keyCombination = new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN);
				if (keyCombination.match(event)) {
					HotKeyMessage hotKeyMessage = new HotKeyMessage(HotKey.CTRL_M);
					eventBus.send(new HotKeyMessageEvent(hotKeyMessage));
				} 
			}
			
		});
		String lastProjectDirectoryName = properties.get(CodeViewProperties.LAST_PROJECT_DIRECTORY);
		if (lastProjectDirectoryName != null && !lastProjectDirectoryName.isEmpty()) {
			FileSystem fileSystem = new FileSystem(lastProjectDirectoryName);
			ProjectDescriptor projectDescriptor = fileSystem.open(ProjectDescriptor.PROJECT_IDENTIFIER);
			if (projectDescriptor != null) {
				Project project = new Project(projectDescriptor, fileSystem, properties, primaryStage, eventBus);
				project.loadContents();
				ProjectPane projectPane = new ProjectPane(project);
				root.setCenter(projectPane);
			}
		}

		primaryStage.getIcons().add(new Image(CodeViewIDEUI.class.getResourceAsStream("/img/icon256.png")));
		primaryStage.setTitle("Codeview IDE");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void importClassFolder(Stage stage) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.showDialog(stage);
	}
}
