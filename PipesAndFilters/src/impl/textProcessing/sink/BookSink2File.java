package impl.textProcessing.sink;

import impl.textProcessing.page.Page;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import framework.filters.sink.AbstractSink;

/**
 * this think writes pages to text-files
 * 
 * @author Chris
 *
 */
public class BookSink2File extends AbstractSink<Page>{
	
	private int nr = 0;
	
	private String path = "";
	
	/**
	 * the path where the pages are saved
	 * 
	 * @param path
	 */
	public BookSink2File(String path) {
		this.path = path;
	}
	
	
	@Override
	public void filter(Page data) {
		File f = new File(path + nr++ + ".txt");
		if (f.exists()) {
			f.delete();
		}
		FileWriter writer = null;
		try {
			writer = new FileWriter(f);
			writer.append("\t\tPage " + nr + "\r\n\n\n");
			writer.append(data.toString());
			writer.close();
			System.out.println("new page added " + nr);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
		
	}
	

}
