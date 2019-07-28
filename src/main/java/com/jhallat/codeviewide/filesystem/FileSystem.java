package com.jhallat.codeviewide.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSystem {

	private final String rootDirectory;
	
	public FileSystem(String rootDirectory) {
		this.rootDirectory = rootDirectory;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Descriptor> T open(String identifier) {

		File projectFile = new File(rootDirectory + "/" + identifier);
		T descriptor = null;
		if (projectFile.exists()) {
			try(FileInputStream fileInput = new FileInputStream(projectFile);
				ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
				descriptor = (T) objectInput.readObject(); 
			} catch (IOException | ClassNotFoundException exception) {
				// TODO Auto-generated catch block
				exception.printStackTrace();
			} 
			
		} else {
			//TODO display error to user
			System.out.println(projectFile.getPath() + " does not exist");
		}
		return descriptor;
		
	}
	
	public void save(Descriptor descriptor) {
		File directory = new File(rootDirectory + "/" + descriptor.getContext());
		if (!directory.exists()) {
			directory.mkdirs();
		}
		File projectFile = new File(directory + "/" + descriptor.getIdentifier());
		try(FileOutputStream fileOutput = new FileOutputStream(projectFile);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
			objectOutput.writeObject(descriptor);
		} catch (IOException e) {
			// TODO Need to alert parent
			e.printStackTrace();
		}		
	}
	

}
