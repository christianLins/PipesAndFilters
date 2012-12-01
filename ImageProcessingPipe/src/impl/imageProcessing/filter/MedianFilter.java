package impl.imageProcessing.filter;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.MedianFilterDescriptor;

import impl.imageProcessing.wrapper.PlanarImageWrapper;
import framework.filters.AbstractFilter;

/**
 * the median filter smooth the image
 * 
 * @author Chris
 *
 */
public class MedianFilter extends AbstractFilter<PlanarImageWrapper, PlanarImageWrapper> {
	
	private int intensity;
	
	public MedianFilter(int intensity) {
		this.intensity = intensity;
	}

	@Override
	public void filter(PlanarImageWrapper data) {
		
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(data.getImage());
		pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
		pb.add(intensity);
		RenderedOp result = JAI.create("MedianFilter", pb);
		
		PlanarImageWrapper planarImageWrapper = new PlanarImageWrapper(result);
		bufferOuts.add(planarImageWrapper);

		
	}

}
