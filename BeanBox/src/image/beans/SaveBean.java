package image.beans;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import image.events.PlanarImageEvent;
import impl.imageProcessing.wrapper.PlanarImageWrapper;

public class SaveBean extends ImageBean {
	
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
		this.path = "";
	}

	

	@Override
	public void handleImageEvent(PlanarImageEvent event) {
		try {
			FileOutputStream fo = new FileOutputStream(path);
			JAI.create("encode", inImageBuffer, fo, "PNG", null);
			JAI.create("filestore", inImageBuffer, path, "PNG", null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outImageBuffer = event.getData();
	}

}
