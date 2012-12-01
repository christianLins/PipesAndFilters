package impl.imageProcessing.filter;

import impl.imageProcessing.wrapper.PlanarImageSegment;
import impl.imageProcessing.wrapper.PlanarImageWrapper;
import framework.filters.AbstractFilter;

/**
 * downcast filter
 * 
 * @author Chris
 *
 */
public class SegmentToWrapFilter extends AbstractFilter<PlanarImageSegment, PlanarImageWrapper> {

	@Override
	public void filter(PlanarImageSegment data) {
		PlanarImageWrapper planarImageWrapper = data.getPlanarImageWrapper();
		bufferOuts.add(planarImageWrapper);
	}

}
