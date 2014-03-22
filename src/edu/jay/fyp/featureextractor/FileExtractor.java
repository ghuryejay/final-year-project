package edu.jay.fyp.featureextractor;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class FileExtractor {

	private final String DIR;
	private final String EXT;
	
	public FileExtractor(String DIR, String EXT) {
		this.DIR = DIR;
		this.EXT = EXT;
	}

	public ArrayList<String> getFiles(){
		final ArrayList<String> listOfFiles = new ArrayList<String>();
		final GenericExtFilter filter = new GenericExtFilter(EXT);
		final File dir = new File(DIR);
		if(dir.isDirectory() == false){
			System.out.println("No such directory");
			return null;
		}
		String[] list = dir.list(filter);
		if (list.length == 0) {
			System.out.println("no files end with : " + EXT);
			return null;
		}
		
		for (String file : list) {
			String temp = new StringBuffer(DIR).append(File.separator)
					.append(file).toString();
			listOfFiles.add(temp);
			System.out.println("file : " + temp);
		}
		return listOfFiles;
	}
	
	public class GenericExtFilter implements FilenameFilter {
		 
		private String ext;
 
		public GenericExtFilter(String ext) {
			this.ext = ext;
		}
		
		@Override
		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}

	}

}
