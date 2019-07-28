package com.jhallat.codeviewide.filesystem;

import java.io.Serializable;

public interface Descriptor extends Serializable {

	String getContext();
	String getIdentifier();
}
