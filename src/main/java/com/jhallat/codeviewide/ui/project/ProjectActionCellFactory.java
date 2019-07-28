package com.jhallat.codeviewide.ui.project;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class ProjectActionCellFactory implements Callback<TreeView<ProjectAction>, TreeCell<ProjectAction>>{

	@Override
	public TreeCell<ProjectAction> call(TreeView<ProjectAction> treeView) {

		TreeCell<ProjectAction> cell = new TreeCell<ProjectAction>() {
			@Override
			public void updateItem(ProjectAction projectAction, boolean empty) {
				super.updateItem(projectAction, empty);
				
				if (empty) {
					setText(null);
				} else {
					setText(projectAction.getDescription());
				}
			}
		};
		cell.getStyleClass().add("tree-table-row-cell");
		cell.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				cell.getItem().onDoubleClick();
			}
		});
		
		return cell;
	}

	
	
}
