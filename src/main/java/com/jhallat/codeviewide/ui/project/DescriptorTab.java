package com.jhallat.codeviewide.ui.project;

import com.jhallat.codeviewide.filesystem.Descriptor;
import com.jhallat.codeviewide.filesystem.DescriptorListener;

import javafx.scene.control.Tab;

public class DescriptorTab extends Tab implements DescriptorListener {

	public DescriptorTab(Descriptor descriptor) {
		super(descriptor.getIdentifier());
		descriptor.addListener(this);
	}

	@Override
	public void onModified(Descriptor descriptor) {
		this.setText(descriptor.getType() + ": " + descriptor.getIdentifier());		
	}
	

	
	
}
