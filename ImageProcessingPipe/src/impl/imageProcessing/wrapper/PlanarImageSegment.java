package impl.imageProcessing.wrapper;

import java.awt.Rectangle;

import javax.media.jai.PlanarImage;

import framework.PipelineDataObject;

/**
 * this data object is able to hold an image and a corresponding segment
 * 
 * @author Chris
 *
 */
public class PlanarImageSegment implements PipelineDataObject<PlanarImageSegment>{

	private Rectangle segment;
	private PlanarImageWrapper imageWrapper;

	public PlanarImageSegment(PlanarImage image, Rectangle segment) {
		imageWrapper = new PlanarImageWrapper(image);
		this.segment = segment;
	}
	
	public Rectangle getSegment() {
		return segment;
	}
	
	public PlanarImageWrapper getPlanarImageWrapper() {
		return imageWrapper;
	}
	
	public PlanarImageSegment clone() {
		return new PlanarImageSegment(getPlanarImageWrapper().getImage(), segment);
	}

}
