package edu.jay.fyp.featureextractor.video;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import edu.jay.fyp.featureextractor.BatchExecutor;

public class ClipFinderUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		String path1 = null;
		File file = fc.getSelectedFile();
		String path = file.getAbsolutePath();
		String qualifiedPath = "\"" + path +"\"";

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bytepath = qualifiedPath.getBytes("UTF-8");
			byte[] ans = digest.digest(bytepath);
			StringBuilder sb = new StringBuilder(2*ans.length);
			for(byte b : ans){
				sb.append(String.format("%02x",b&0xff));
			}
			path1 = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(path);
		String destinationPath = "\"D:\\fyp\\clipExtractor\\smallclips\\"+path1+".avi\"";
		String destPath = "D:\\fyp\\hollywood\\videoclips\\"+path1+".avi";
		File copyBatchFile = new File("D:\\fyp\\clipExtractor\\commands.bat");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(copyBatchFile);
			DataOutputStream dos = new DataOutputStream(fos);
			String command = "copy " + qualifiedPath + " " + destinationPath + "\n";
			dos.writeBytes(command);
			dos.writeBytes("exit\n");
			BatchExecutor batchExecutor = new BatchExecutor();
			batchExecutor.executeBatch("D:\\fyp\\clipExtractor\\commands.bat");
			ClipExtractorProcessor cep = new ClipExtractorProcessor();
			cep.process(destinationPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
