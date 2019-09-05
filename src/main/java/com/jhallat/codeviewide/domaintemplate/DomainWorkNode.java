package com.jhallat.codeviewide.domaintemplate;

import com.jhallat.codeviewide.filesystem.Descriptor;
import com.jhallat.codeviewide.ui.WorkNode;
import com.jhallat.codeviewide.ui.project.Project;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DomainWorkNode implements WorkNode {

	private final Project project;
	private final DomainDescriptor domainDescriptor;
	
	
	public DomainWorkNode(Project project) {
		this.project = project;
		this.domainDescriptor = new DomainDescriptor();
		this.domainDescriptor.setDomain("Domain");
	}
	
	@Override
	public Node createNode() {
		
		BorderPane rootPane = new BorderPane();
		rootPane.getStyleClass().add("code-pane");

		GridPane descriptionPane = new GridPane();
		descriptionPane.getStyleClass().add("code-grid");
		Label descriptionLabel = new Label("Description");
		TextField descriptionText = new TextField();
		descriptionText.setPrefWidth(300);
		descriptionText.setOnKeyReleased(event -> {
			this.domainDescriptor.setDomain(descriptionText.getText());
		});
		descriptionPane.add(descriptionLabel, 0, 0);
		descriptionPane.add(descriptionText, 1, 0);
		
		BorderPane domainPane = new BorderPane();	

		Pane domainDiagram = new Pane();
		domainDiagram.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Hyperlink addEventLink = new Hyperlink("Add Event");
		addEventLink.setOnAction(event -> {
			DomainEventTag tag = new DomainEventTag();
			domainDiagram.getChildren().add(tag);
		});

		domainPane.setTop(addEventLink);
		domainPane.setCenter(domainDiagram);
		
		rootPane.setTop(descriptionPane);
		rootPane.setCenter(domainPane);
		
		return rootPane;
	}

	@Override
	public Descriptor getDescriptor() {
		return this.domainDescriptor;
	}


	
}
