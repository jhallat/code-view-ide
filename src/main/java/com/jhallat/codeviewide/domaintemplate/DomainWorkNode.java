package com.jhallat.codeviewide.domaintemplate;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DomainWorkNode implements WorkNode, DomainOptionSelectEventHandler {

	private final Project project;
	private final DomainDescriptor domainDescriptor;
	private final List<DomainCategoryOption> categoryOptions = new ArrayList<>();
	
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
		descriptionPane.getStyleClass().add("code-form");
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
		domainDiagram.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				System.out.println(event.getX() + ":" + event.getY());
				DomainEventTag newTag = new DomainEventTag(event.getX(), event.getY());
				domainDiagram.getChildren().add(newTag);
			}
		});
		
		var optionBox = new HBox(6);
		optionBox.getStyleClass().add("option-bar");
		var defaultCategoryModel = new DomainCategoryModel("Event", Color.ORANGE);
		var defaultCategoryOption = new DomainCategoryOption(defaultCategoryModel, 0);
		categoryOptions.add(defaultCategoryOption);
		defaultCategoryOption.setFocus(true);
		defaultCategoryOption.setOnSelectEventHandler((model, index) -> {
			setOptionFocus(index);
		}); 
		optionBox.getChildren().add(defaultCategoryOption);
		
		Hyperlink addEventCategoryLink = new Hyperlink("Add Category");
		addEventCategoryLink.setOnAction(event -> {
			var categoryDialog = new NewDomainCategoryDialog();
			var result = categoryDialog.showAndWait();
			if (result.isPresent()) {
				var newCategoryOption = new DomainCategoryOption(result.get(), categoryOptions.size());
				categoryOptions.add(newCategoryOption);
				optionBox.getChildren().add(newCategoryOption);
				newCategoryOption.setOnSelectEventHandler((model, index) -> {
					setOptionFocus(index);
				}); 
			}
		});


		HBox linkBox = new HBox(3);
		linkBox.getChildren().addAll(addEventCategoryLink);


		
		BorderPane drawDomainPane = new BorderPane();
		drawDomainPane.setTop(optionBox);
		drawDomainPane.setCenter(domainDiagram);
		
		domainPane.setTop(linkBox);
		domainPane.setCenter(drawDomainPane);
		
		rootPane.setTop(descriptionPane);
		rootPane.setCenter(domainPane);
		
		return rootPane;
	}

	private void setOptionFocus(int index) {
		
		for (int i = 0; i < categoryOptions.size(); i++) {
			if (i == index) {
				categoryOptions.get(i).setFocus(true);
			} else {
				categoryOptions.get(i).setFocus(false);
			}
		}
		
	}
	
	@Override
	public Descriptor getDescriptor() {
		return this.domainDescriptor;
	}

	@Override
	public void onSelected(DomainCategoryModel model, int index) {
		// TODO Auto-generated method stub
		
	}


	
}
