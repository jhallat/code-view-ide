package com.jhallat.codeviewide.domaintemplate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DomainEventTag extends Group {

	private static final int TAG_WIDTH = 100;
	private static final int TAG_HEIGHT = 100;
	private static final int TEXT_EDIT_PADDING = 4;
	private static final int TEXT_X_PADDING = 10;
	private static final int TEXT_Y_PADDING = 20;
	
	public DomainEventTag(DomainCategoryModel categoryModel, double startX, double startY) {
		super();
		Group contentGroup = new Group();
		Rectangle background = new Rectangle();
		background.setWidth(TAG_WIDTH);
		background.setHeight(TAG_HEIGHT);
		background.setFill(categoryModel.getColor());
		background.setX(startX);
		background.setY(startY);
		TextArea editableContentText = new TextArea();
		editableContentText.setWrapText(true);
		editableContentText.setPrefWidth(TAG_WIDTH - TEXT_EDIT_PADDING * 2);
		editableContentText.setPrefHeight(TAG_HEIGHT - TEXT_EDIT_PADDING * 2);
		editableContentText.setLayoutX(startX + TEXT_EDIT_PADDING);
		editableContentText.setLayoutY(startY + TEXT_EDIT_PADDING);
		editableContentText.setVisible(false);
		Text contentText = new Text();
		contentText.setWrappingWidth(TAG_WIDTH - TEXT_X_PADDING * 2);
		contentText.maxHeight(TAG_HEIGHT - TEXT_Y_PADDING * 2);
		contentText.setX(startX + TEXT_X_PADDING);
		contentText.setY(startY + TEXT_Y_PADDING);
		contentGroup.getChildren().addAll(background, editableContentText, contentText);
		this.getChildren().add(contentGroup);
		addFilters(this, contentGroup, editableContentText, contentText);
	}
	
	
	private void addFilters(Group wrapper, Node node, TextArea editableContentText, Text contentText) {
		
		final DragContext dragContext = new DragContext();
		
		wrapper.addEventFilter(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						dragContext.setMouseAnchorX(event.getX());
						dragContext.setMouseAnchorY(event.getY());
						dragContext.setInitialTranslateX(node.getTranslateX());
						dragContext.setInitialTranslateY(node.getTranslateY());
					}
		});
		
		wrapper.addEventFilter(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						node.setTranslateX(dragContext.getInitialTranslateX() + event.getX() - dragContext.getMouseAnchorX());
						node.setTranslateY(dragContext.getInitialTranslateY() + event.getY() - dragContext.getMouseAnchorY());
					}
		});
		
		wrapper.addEventFilter(MouseEvent.MOUSE_CLICKED, 
			event -> {
				if (event.getClickCount() == 2) {
					contentText.setVisible(false);
					editableContentText.setVisible(true);
					editableContentText.requestFocus();
					event.consume();
				}
		});
		
		editableContentText.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!observable.getValue()) {
					contentText.setText(editableContentText.getText());
					editableContentText.setVisible(false);
					contentText.setVisible(true);
				}
			}
			
		});
	}
	
}
