package image.beans;

import java.awt.Graphics;

import image.events.JointPointSegmentEvent;
import image.events.PlanarImageEvent;
import impl.imageProcessing.wrapper.PlanarImageWrapper;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

@SuppressWarnings("serial")
public class LoadBean extends ImageBean {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8397233982118121177L;
	
	private String imageSourcePath;
	
	public LoadBean() {
		imageSourcePath = "c:/loetstellen.jpg";
		handleImageEvent(null);
	}

	public String getImageSourcePath() {
		return imageSourcePath;
	}

	public void setImageSourcePath(String imageSourcePath) {
		String old = this.imageSourcePath;
		this.imageSourcePath = imageSourcePath;
		sup.firePropertyChange("imageSourcePath", old, imageSourcePath);
	}


	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		String s = "load image";
		graphics.drawChars(s.toCharArray(), 0, s.length(), 20, 25);
	}
	

	@Override
	protected void handleImageEvent(PlanarImageEvent event) {
		outImageBuffer = inImageBuffer = JAI.create("fileload", imageSourcePath);	
		
	}
}
