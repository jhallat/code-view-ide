package com.jhallat.codeviewide.domaintemplate;

import com.jhallat.codeviewide.filesystem.Descriptor;
import com.jhallat.codeviewide.ui.WorkNode;
import com.jhallat.codeviewide.ui.project.Project;

import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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
		
		SplitPane nodePane = new SplitPane();
		nodePane.setOrientation(Orientation.VERTICAL);

		GridPane descriptionPane = new GridPane();
		descriptionPane.getStyleClass().add("code-pane");
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
		
		Hyperlink addEventLink = new Hyperlink("Add Event");
		addEventLink.setOnAction(event -> {
			//Node eventText = makeDraggable(new TextField());
			//domainDiagram.getChildren().add(eventText);
		});

		domainPane.setTop(addEventLink);
		domainPane.setCenter(domainDiagram);
		
		nodePane.getItems().addAll(descriptionPane, domainPane);
		
		return nodePane;
	}

	@Override
	public Descriptor getDescriptor() {
		return this.domainDescriptor;
	}


	
}
