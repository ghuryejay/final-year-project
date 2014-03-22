package edu.jay.fyp.featureextractor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class BatchExecutor {

	 public void executeBatch(String pathToBatch){
	        try {
	        	Process p = Runtime.getRuntime().exec("cmd /c start "+pathToBatch);
	            p.waitFor();
	            InputStream in = p.getInputStream();
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 
	            int c = -1;
	            while((c = in.read()) != -1)
	            {
	                baos.write(c);
	            }
	 
	            String response = new String(baos.toByteArray());
	            System.out.println("Response From Exe : "+response);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
