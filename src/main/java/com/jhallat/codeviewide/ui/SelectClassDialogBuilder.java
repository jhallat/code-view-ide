package com.jhallat.codeviewide.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jhallat.codeviewide.model.ClassModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;

public class SelectClassDialogBuilder {
	
	public static Dialog<List<ClassModel>> build(String title, Set<ClassModel> classModels) {
		Dialog<List<ClassModel>> dialog = new Dialog<>();
		dialog.setTitle(title);
		dialog.getDialogPane().getStylesheets().add("style.css");
		
		//List<String> classNames = classModels.stream().map(item -> item.getClassName()).collect(Collectors.toList());
		
		ObservableList<ClassModel> classList = FXCollections.observableArrayList(classModels);
		ListView<ClassModel> classListView = new ListView<>();
		classListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		classListView.getItems().addAll(classList);
		classListView.setCellFactory(new Callback<ListView<ClassModel>, ListCell<ClassModel>> () {
			@Override
			public ListCell<ClassModel> call(ListView<ClassModel> list) {
				return new ListCell<ClassModel>() {
					@Override
					protected void updateItem(ClassModel item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(item.getClassName());
						}
					}
				};
			}
			
		});
		
		BorderPane content = new BorderPane();
		content.setCenter(classListView);
		
		Button addButton = new Button("Add");
		addButton.getStyleClass().add("dialog-button");
		addButton.setOnAction(event -> {
			List<ClassModel> selectedItems = classListView.getSelectionModel().getSelectedItems();
			dialog.setResult(selectedItems);
			dialog.close();
		});
		Button cancelButton = new Button("Cancel");
		cancelButton.getStyleClass().add("dialog-button");
		cancelButton.setOnAction(item -> {
			dialog.setResult(new ArrayList<>());;
			dialog.close();
		});
		HBox buttonBar = new HBox(6);
		buttonBar.getStyleClass().add("button-bar");
		HBox.setHgrow(addButton, Priority.ALWAYS);
		HBox.setHgrow(cancelButton, Priority.ALWAYS);
		buttonBar.getChildren().addAll(addButton, cancelButton);
		buttonBar.setAlignment(Pos.CENTER_RIGHT);
		content.setBottom(buttonBar);		
		
		dialog.getDialogPane().setContent(content);
		
		return dialog;
	}

	
	
}
