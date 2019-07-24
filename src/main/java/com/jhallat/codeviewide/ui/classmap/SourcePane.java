package com.jhallat.codeviewide.ui.classmap;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.jhallat.codeviewide.model.ClassModel;
import com.jhallat.codeviewide.ui.BuildPath;
import com.jhallat.codeviewide.ui.SelectClassDialogBuilder;

import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SourcePane extends BorderPane {

	private VBox sourceHeading = new VBox();
	private VBox sourceContents = new VBox();
	private Hyperlink addSourceClassLink = new Hyperlink("Add class");
	private final BuildPath buildPath;
	
	public SourcePane(BuildPath buildPath) {
		this.buildPath = buildPath;
		addSourceClassLink.setOnAction(event -> {
			List<ClassModel> classModels = addSourceClass();
			for (ClassModel classModel : classModels) {
				TreeView<ClassMethodDefinition> classTreeView = createSourceClassTree(classModel);
				sourceContents.getChildren().add(classTreeView);
			}
		});
		Label sourceLabel = new Label("Source");
		sourceHeading.getChildren().addAll(sourceLabel, addSourceClassLink);
		this.setTop(sourceHeading);
		this.setCenter(sourceContents);		
	}
	
	private List<ClassModel> addSourceClass() {
		
		Dialog<List<ClassModel>> dialog = SelectClassDialogBuilder.build("Add Source Class", buildPath.getClassModels());
		Optional<List<ClassModel>> classesToAdd = dialog.showAndWait();
		if (classesToAdd.isPresent()) {
			return classesToAdd.get();
		}
		return Collections.emptyList();
	}
	
	private TreeView<ClassMethodDefinition> createSourceClassTree(ClassModel classModel) {
		ClassMethodDefinition definition = new ClassMethodDefinition(classModel.getClassName(), classModel.getClassName(), false);
		TreeItem<ClassMethodDefinition> rootItem = new TreeItem<> (definition);
		rootItem.setExpanded(true);
		Class<?> rootClass = classModel.getClassDefinition();
		Method[] methods = rootClass.getMethods();		
		for (Method method : methods) {
			if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
				String name = method.getName();
				String returnType = method.getReturnType().getName();
				ClassMethodDefinition methodDefinition = new ClassMethodDefinition(name + " : " + returnType ,
						                                                           rootClass.getSimpleName() + "." + name.substring(3),
						                                                           true);
				TreeItem<ClassMethodDefinition> item = new TreeItem<>(methodDefinition);
				if (!method.getReturnType().isPrimitive() && !method.getName().equals("getClass")) {
					populateReturnItem(item, method.getReturnType());
				}
				rootItem.getChildren().add(item);
			}
		}
		TreeView<ClassMethodDefinition> tree = new TreeView<> (rootItem);
		tree.setCellFactory(new SourceTreeCellFactory());
		return tree;
	}
	
	private void populateReturnItem(TreeItem<ClassMethodDefinition> item, Class<?> subClass) {
		
		Method[] methods = subClass.getMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get") && Modifier.isPublic(method.getModifiers())) {
				String name = method.getName();
				String returnType = method.getReturnType().getName();
				ClassMethodDefinition methodDefinition = new ClassMethodDefinition(name + " : " + returnType , 
						                                                           item.getValue().getPath() + "." + name.substring(3),
						                                                           true);
				TreeItem<ClassMethodDefinition> subitem = new TreeItem<>(methodDefinition);
				if (!method.getReturnType().isPrimitive()  && !method.getName().equals("getClass")) {
					populateReturnItem(subitem, method.getReturnType());
				}
				item.getChildren().add(subitem);
			}
		}
	}


}
