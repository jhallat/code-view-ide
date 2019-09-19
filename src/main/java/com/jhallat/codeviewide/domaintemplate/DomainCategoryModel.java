package com.jhallat.codeviewide.domaintemplate;

import javafx.scene.paint.Color;

public class DomainCategoryModel {

	private String description;
	private Color color;
	
	public DomainCategoryModel(String description, Color color) {
		this.description = description;
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
