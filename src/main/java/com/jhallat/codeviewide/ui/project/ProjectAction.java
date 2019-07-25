package com.jhallat.codeviewide.ui.project;


public interface ProjectAction {

	String getDescription();
	default void onDoubleClick() {};
}
