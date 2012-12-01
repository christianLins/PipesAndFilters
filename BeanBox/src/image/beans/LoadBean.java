package image.beans;

import image.events.PlanarImageEvent;

import java.awt.Graphics;

import javax.media.jai.JAI;

public class LoadBean extends AbstractImageBean {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8397233982118121177L;
	
	private String imageSourcePath;
	
	public LoadBean() {
		super();
		setImageSourcePath("c:/loetstellen.jpg");
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
//		String s = "load image";
//		graphics.drawChars(s.toCharArray(), 0, s.length(), 20, 25);
	}
	
	@Override
	protected void beforeHandleImageEvent() {
		try {
			inImageBuffer = JAI.create("fileload", imageSourcePath);
		} catch (Exception e){
			System.out.println("image could not be loaded " + imageSourcePath);
		}
		 	
	}
	

	@Override
	protected void handleImageEvent(PlanarImageEvent event) {
		outImageBuffer = inImageBuffer;	
		
	}
}
