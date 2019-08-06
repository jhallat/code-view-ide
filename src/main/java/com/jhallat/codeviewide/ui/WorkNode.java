package com.jhallat.codeviewide.ui;

import com.jhallat.codeviewide.filesystem.Descriptor;

import javafx.scene.Node;

public interface WorkNode {

	Node createNode();
	Descriptor getDescriptor();
}
