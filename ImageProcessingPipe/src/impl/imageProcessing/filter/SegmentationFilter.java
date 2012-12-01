package impl.imageProcessing.filter;

import impl.imageProcessing.wrapper.PlanarImageSegment;
import impl.imageProcessing.wrapper.PlanarImageWrapper;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;

import javax.media.jai.PlanarImage;

import framework.filters.AbstractFilter;

/**
 * this class provides functionality to segment a image to specific area
 * in this case we can segment by a rectangle
 * 
 * @author Chris
 *
 */
public class SegmentationFilter extends AbstractFilter<PlanarImageWrapper,PlanarImageSegment> {
	
	private Collection<Rectangle> rectangles = new LinkedList<>();

	public SegmentationFilter(Rectangle segment) {
		super();
		rectangles.add(segment);
	}
	
	public SegmentationFilter(Collection<Rectangle> segments) {
		super();
		rectangles = segments;
	}

	/**
	 * segment the image to a specific area
	 */
	@Override
	public void filter(PlanarImageWrapper data) {
		for(Rectangle rectangle : rectangles) {
			PlanarImage image = PlanarImage.wrapRenderedImage(data.getImage().getAsBufferedImage(rectangle, data.getImage().getColorModel()));
			PlanarImageSegment planarImageWrapper = new PlanarImageSegment(image, rectangle);
			bufferOuts.add(planarImageWrapper);
		}
	}

}
