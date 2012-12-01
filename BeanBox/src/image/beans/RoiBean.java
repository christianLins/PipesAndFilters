package image.beans;

import java.awt.Rectangle;

import javax.media.jai.PlanarImage;

import image.events.PlanarImageEvent;

public class RoiBean extends ImageBean {
	
	private int x;
	private int y;
	private int hight;
	private int width;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		int old = this.x;
		this.x = x;
		sup.firePropertyChange("x", old, x);
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		int old = this.y;
		this.y = y;
		sup.firePropertyChange("y", old, y);
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		int old = this.hight;
		this.hight = hight;
		sup.firePropertyChange("hight", old, hight);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		int old = this.width;
		this.width = width;
		sup.firePropertyChange("width", old, width);
	}

	
	public RoiBean() {
		this.x = 0;
		this.y = 0;
		this.hight = 100;
		this.width = 500;
	}

	@Override
	public void handleImageEvent(PlanarImageEvent event) {		
		outImageBuffer = PlanarImage.wrapRenderedImage(inImageBuffer.getAsBufferedImage(new Rectangle(x, y, width, hight), inImageBuffer.getColorModel()));
	}


}
