package com.jhallat.codeviewide.domaintemplate;

import com.jhallat.codeviewide.filesystem.Descriptor;

public class DomainDescriptor extends Descriptor {

	private static final long serialVersionUID = 5815453114343062259L;
	private String path;
	private String domain;
	
	public String getPath() {
		if (path == null) {
			return "";
		}
		return this.path;
	}
	
	public void setPath(String path) { 
		this.path = path;
	}
	
	public String getDomain() {
		if (path == null) {
			return "";
		}
		return this.domain;
	}
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	@Override
	public String getType() {
		return "Domain";
	}

	@Override
	public String getContext() {
		return getPath();
	}

	@Override
	public String getIdentifier() {
		return getDomain();
	}

}
