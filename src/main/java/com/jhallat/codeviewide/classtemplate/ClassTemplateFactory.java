package com.jhallat.codeviewide.classtemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jhallat.codeviewide.ui.WorkNode;
import com.jhallat.codeviewide.ui.message.MessageEventBus;
import com.jhallat.codeviewide.ui.project.Project;


public class ClassTemplateFactory {
	
	private Map<String, WorkNode> workNodeMap = new HashMap<>();
	
	
	public void loadForProject(Project project) {
		
		workNodeMap.put("Object", new ObjectWorkNode(project));
		
	}
	
	public List<String> getTemplates() {

		return workNodeMap.keySet().stream().collect(Collectors.toList());
		
	}

	public WorkNode getWorkNode(String template) {
		return workNodeMap.get(template);
	}
	

}
