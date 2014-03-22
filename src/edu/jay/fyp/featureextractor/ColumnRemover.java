package edu.jay.fyp.featureextractor;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColumnRemover {

	private static final String DIR = "D:\\fyp\\root";
	private final ArrayList<String> initCommands = new ArrayList<String>();
	
	public void removeColumns(String filePath){
		int dirLength = DIR.length();
		File removeColumnBatch = new File(DIR + "\\columnRemover.bat");
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(removeColumnBatch);
		}catch(Exception e){
			e.printStackTrace();
		}
		DataOutputStream dos = new DataOutputStream(fos);
		initCommands.add("cd..\n");
		initCommands.add("cd..\n");
		initCommands.add("D:\n");
		initCommands.add("cd fyp\n");
		initCommands.add("cd root\n");
		for(int i = 0;i < initCommands.size();i++)
			try {
				dos.writeBytes(initCommands.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		String csvFileWithoutExt = filePath.substring(dirLength+1,filePath.length()-4);
		String command = "csvfix exclude -f 1,2,3,4,5,6,7 -o "+csvFileWithoutExt+"-new.csv " + csvFileWithoutExt+".csv"+"\n";
		try {
			dos.writeBytes(command);
			dos.writeBytes("exit\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		new BatchExecutor().executeBatch(DIR + "\\columnRemover.bat");
	}
}
