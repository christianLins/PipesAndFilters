package image.beans;

import image.events.PlanarImageEvent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

public class SaveBean extends AbstractImageBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path;

	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		String old = this.path;
		this.path = path;
		sup.firePropertyChange("path", old, path);
	}



	public SaveBean() {
		super();
		this.path = "c:/output/myOutput.png";
	}

	

	@Override
	public void handleImageEvent(PlanarImageEvent event) {
		PlanarImage temp = inImageBuffer.createSnapshot();
		
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(path);
			JAI.create("encode", temp, fo, "PNG", null);
			JAI.create("filestore", temp, path, "PNG", null);
			System.out.println("image successfully saved");
		} catch (FileNotFoundException e) {
			System.out.println("problems saving image");
			e.printStackTrace();
		} finally {
			if(fo != null) {
				try {
					fo.flush();
					fo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		outImageBuffer = temp;
	}

}
