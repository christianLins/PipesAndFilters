package image.beans;

import image.events.PlanarImageEvent;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;


public class ThresholdBean extends AbstractImageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void handleImageEvent(PlanarImageEvent event) {
        double[] lowArea = { low };
        double[] highArea = { high };
        double[] mapArea = { map };

        ParameterBlock pb = new ParameterBlock();
        pb.addSource(inImageBuffer);
        pb.add(lowArea);
        pb.add(highArea);
        pb.add(mapArea);

        RenderedOp newImage = JAI.create("threshold", pb);
        
        outImageBuffer = newImage;
	}
	
	private double low;
	private double high;
	private double map;
	
	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		double old = this.low;
		this.low = low;
		sup.firePropertyChange("low", old, low);
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		double old = this.high;
		this.high = high;
		sup.firePropertyChange("high", old, high);
	}

	public double getMap() {
		return map;
	}

	public void setMap(double map) {
		double old = this.map;
		this.map = map;
		sup.firePropertyChange("map", old, map);
	}

	public ThresholdBean() {
		super();
		this.low = 0;
		this.high = 40;
		this.map = 255;
	}


}
