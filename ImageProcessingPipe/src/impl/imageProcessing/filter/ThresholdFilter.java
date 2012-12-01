package impl.imageProcessing.filter;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;

import impl.imageProcessing.wrapper.PlanarImageWrapper;
import framework.filters.AbstractFilter;

/**
 * this filter maps a specific brightness value to an specific value (map)
 * 
 * @author Chris
 *
 */
public class ThresholdFilter extends AbstractFilter<PlanarImageWrapper, PlanarImageWrapper> {
	
     
	private double low;
	private double high;
	private double map;
	
	public ThresholdFilter(double low, double high, double map) {
		this.low = low;
		this.high = high;
		this.map = map;
	}

	@Override
	public void filter(PlanarImageWrapper data) {
		PlanarImage image = data.getImage();
       

        double[] lowArea = { low };
        double[] highArea = { high };
        double[] mapArea = { map };

        ParameterBlock pb = new ParameterBlock();
        pb.addSource(image);
        pb.add(lowArea);
        pb.add(highArea);
        pb.add(mapArea);

        RenderedOp newImage = JAI.create("threshold", pb);
        PlanarImageWrapper planarImageWrapper = new PlanarImageWrapper(newImage);
        bufferOuts.add(planarImageWrapper);
	}

}
