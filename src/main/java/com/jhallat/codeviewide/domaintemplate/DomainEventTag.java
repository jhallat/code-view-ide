package com.jhallat.codeviewide.domaintemplate;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DomainEventTag extends Group {

	
	public DomainEventTag() {
		super();
		Group contentGroup = new Group();
		Rectangle background = new Rectangle();
		background.setWidth(75);
		background.setHeight(75);
		background.setFill(Color.ORANGE);
		background.setX(25);
		background.setY(25);
		TextField contentText = new TextField();
		contentText.setPrefWidth(71);
		contentText.setPrefHeight(71);
		contentText.setLayoutX(27);
		contentText.setLayoutY(27);
		contentGroup.getChildren().addAll(background, contentText);
		this.getChildren().add(contentGroup);
		makeDraggable(this, contentGroup);
	}
	
	
	private void makeDraggable(Group wrapper, Node node) {
		
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
		
	}
	
}
