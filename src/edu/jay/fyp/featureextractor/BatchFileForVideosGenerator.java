package edu.jay.fyp.featureextractor;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BatchFileForVideosGenerator {

	private File file = null;
	private FileOutputStream fos = null;
	private DataOutputStream dos = null;
	final ArrayList<String> initialCommands = new ArrayList<String>();

	public String generateBatchForMultipleVideos(String FILE_DIR,
			String FILE_EXT) {
		final ArrayList<String> listOfVideos = new FileExtractor(FILE_DIR,
				FILE_EXT).getFiles();
		file = new File(FILE_DIR + "\\commands.bat");
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DataOutputStream dos = new DataOutputStream(fos);
		initBatchFile1();
		String str = listOfVideos.get(0);
		System.out.println(str.length());
		System.out.println(str.substring(28, 51));
		for (String videoName : listOfVideos) {
			generateBatchFileForSingleVideo(videoName, FILE_DIR);
		}
		return FILE_DIR + "\\commands.bat";
	}

	public String generateBatchFileForSingleVideo(String filePath, String dir) {
		if (file == null) {
			file = new File(dir + "\\commands.bat");
			try {
				fos = new FileOutputStream(file);
				dos = new DataOutputStream(fos);
				if (dir.equals("D:\\fyp\\hollywood\\videoclips"))
					initBatchFile1();
				else if (dir.equals("D:\\fyp\\clipExtractor\\smallclips"))
					initBatchFile2();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		String videofor = filePath.substring(dir.length()+2, filePath.length()-1);
		String command = "stipdet.exe" + " -f " + "\"" + videofor + "\""
				+ " -o " + "output.txt" + "\n";
		try {
			dos.writeBytes(command);
			dos.writeBytes("exit\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dir + "\\commands.bat";
	}

	private void initBatchFile1() {
		initialCommands.add("cd..\n");
		initialCommands.add("cd..\n");
		initialCommands.add("D:\n");
		initialCommands.add("cd fyp\n");
		initialCommands.add("cd hollywood\n");
		initialCommands.add("cd videoclips\n");
		for (int i = 0; i < initialCommands.size(); i++) {
			try {
				dos.writeBytes(initialCommands.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initBatchFile2() {
		initialCommands.add("cd..\n");
		initialCommands.add("cd..\n");
		initialCommands.add("D:\n");
		initialCommands.add("cd fyp\n");
		initialCommands.add("cd clipExtractor\n");
		initialCommands.add("cd smallclips\n");
		for (int i = 0; i < initialCommands.size(); i++) {
			try {
				dos.writeBytes(initialCommands.get(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
