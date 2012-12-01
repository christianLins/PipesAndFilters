package impl.textProcessing.source;

import impl.textProcessing.letter.Letter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import framework.filters.source.AbstractSource;

public class TextSource extends AbstractSource<Letter> {
	
	private boolean sourceRead = false;

	private String filePath;
	
	/**
	 * enter the path of the file to set as source of the pipe
	 * to insert plain text, create a file and set the path
	 * 
	 * @param filePath
	 */
	public TextSource(String filePath) {
		this.filePath = filePath;
	}

	
		
		

	@Override
	public Letter getSource() {
		if (!sourceRead) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));
				char[] cbuf = new char[10];
				
				while(true) {
					int chars = reader.read(cbuf);
					if (chars == -1) {
						sourceRead = true;
						return null;
					}
					for (int i = 0; i < chars; i++) {
						buffer.add(new Letter(cbuf[i]));		
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


}
