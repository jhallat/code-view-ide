package com.jhallat.codeviewide.domaintemplate;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class DomainCategoryOption extends Pane {
	
	private final Text tagDescription;
	private DomainOptionSelectEventHandler eventHandler; 
	
	private DomainCategoryModel model;
	
	private boolean hasFocus;
	
	public DomainCategoryOption(DomainCategoryModel model, int index) {
		super();
		this.model = model;
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		Rectangle tagColor = new Rectangle();
		tagColor.setFill(model.getColor());
		tagColor.setStroke(Color.BLACK);
		tagColor.setWidth(20);
		tagColor.setHeight(20);
		tagColor.setX(5);
		tagColor.setY(5);
		tagDescription = new Text(model.getDescription());
		tagDescription.setX(30);
		tagDescription.setY(20);
		this.getChildren().addAll(tagColor, tagDescription);
		this.setOnMouseClicked(event -> {
			if (eventHandler != null) {
				this.eventHandler.onSelected(model, index);
			}
		});
	}

	public DomainCategoryModel getModel() {
		return model;
	}
	
	public boolean hasFocus() {
		return this.hasFocus;
	}
	
	protected void setFocus(boolean focus) {
		this.hasFocus = focus;
		tagDescription.setUnderline(focus);
	}
	
	protected void setOnSelectEventHandler(DomainOptionSelectEventHandler handler) {
		this.eventHandler = handler;
	}
}
