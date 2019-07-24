package com.jhallat.codeviewide.ui.classmap;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;

public class SourceTreeCellFactory extends CopyTreeCellFactory implements Callback<TreeView<ClassMethodDefinition>, TreeCell<ClassMethodDefinition>> {
	
	@Override 
	public TreeCell<ClassMethodDefinition> call(TreeView<ClassMethodDefinition> treeView) {
		
		TreeCell<ClassMethodDefinition> cell = new TreeCell<ClassMethodDefinition>() {
			@Override
			public void updateItem(ClassMethodDefinition item, boolean empty) {
				super.updateItem(item, empty);
				
				if (empty) {
					setText(null);
				} else {
					setText(item.getDescription());
				}
			}
		};
		cell.setOnDragDetected((MouseEvent event) -> {
			TreeItem<ClassMethodDefinition> draggedItem = cell.getTreeItem();
			if (draggedItem.getParent() == null) {
				return;
			}
			Dragboard dragboard = cell.startDragAndDrop(TransferMode.ANY);
			ClipboardContent content = new ClipboardContent();
			content.put(JAVA_FORMAT, draggedItem.getValue());
			dragboard.setContent(content);
			dragboard.setDragView(cell.snapshot(null,null));
			event.consume();
		});
		return cell;
	}

}
