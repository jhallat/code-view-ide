package com.jhallat.codeviewide.ui.project;

import javafx.scene.control.ContextMenu;

public interface ProjectAction {

	String getDescription();
	default void onDoubleClick() {};
	default ContextMenu getContextMenu() { return null; }
}
