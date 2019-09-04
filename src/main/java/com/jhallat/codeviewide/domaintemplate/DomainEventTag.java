package com.jhallat.codeviewide.domaintemplate;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class DomainEventTag extends Group {

	
	public DomainEventTag() {
		super();
		makeDraggable(this);
		Rectangle background = new Rectangle();
		
	}
	
	
	private void makeDraggable(Node node) {
		
		final DragContext dragContext = new DragContext();
		
		node.addEventFilter(MouseEvent.MOUSE_PRESSED, 
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						dragContext.setMouseAnchorX(event.getX());
						dragContext.setMouseAnchorY(event.getY());
						dragContext.setInitialTranslateX(node.getTranslateX());
						dragContext.setInitialTranslateY(node.getTranslateY());
					}
		});
		
		node.addEventFilter(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						node.setTranslateX(dragContext.getInitialTranslateX() + event.getX() - dragContext.getMouseAnchorX());
						node.setTranslateY(dragContext.getInitialTranslateY() + event.getY() - dragContext.getMouseAnchorY());
					}
		});
		
	}
	
}
