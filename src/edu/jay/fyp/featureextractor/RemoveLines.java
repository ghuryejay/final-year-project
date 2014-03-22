package edu.jay.fyp.featureextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class RemoveLines {

	public void delete(String filename, int startline, int numlines) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			StringBuffer sb = new StringBuffer("");
			int linenumber = 1;
			String line;
			while ((line = br.readLine()) != null) {
				if (linenumber < startline
						|| linenumber >= startline + numlines)
					sb.append(line + "\n");
				linenumber++;
			}
			if (startline + numlines > linenumber)
				System.out.println("End of file reached.");
			br.close();
			FileWriter fw = new FileWriter(new File(filename));
			fw.write(sb.toString());
			fw.close();
		} catch (Exception e) {
			System.out.println("Something went horribly wrong: "
					+ e.getMessage());
		}
	}

}
