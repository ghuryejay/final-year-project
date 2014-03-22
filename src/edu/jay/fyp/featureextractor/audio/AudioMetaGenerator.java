package edu.jay.fyp.featureextractor.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AudioMetaGenerator {

	public Map<String, String> getMetaData(String audioFileLoc) {
		Map<String, String> meta = new HashMap<String, String>();
		try {

			InputStream input = new FileInputStream(new File(audioFileLoc));
			ContentHandler handler = new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new Mp3Parser();
			ParseContext parseCtx = new ParseContext();
			parser.parse(input, handler, metadata, parseCtx);
			input.close();

			// List all metadata
			String[] metadataNames = metadata.names();

			for (String name : metadataNames) {
				int index = name.indexOf(":");
				String temp;
				temp = name.substring(index + 1);
				meta.put(temp, metadata.get(name));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		return meta;
	}
}
