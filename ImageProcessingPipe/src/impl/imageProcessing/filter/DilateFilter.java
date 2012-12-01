package impl.imageProcessing.filter;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;

import impl.imageProcessing.wrapper.PlanarImageWrapper;
import framework.filters.AbstractFilter;

/**
 * this class dilates a image by a predefined or external matrix (set kernel data)
 * to increase the impact you can set the repetitions from 1 to ...
 * 
 * @author Chris
 *
 */
public class DilateFilter extends AbstractFilter<PlanarImageWrapper, PlanarImageWrapper> {
	
	private float[] kernelData = { 0, 1, 0, 
								   1, 1, 1, 
								   0, 1, 0 }; // default kernel
	private int repetitions = 1;

	@Override
	public void filter(PlanarImageWrapper data) {
		PlanarImage image = data.getImage();
		
		for(int i = 0; i < repetitions; i++) {
			KernelJAI kernel = new KernelJAI(3, 3, kernelData);

			ParameterBlock pb = new ParameterBlock();
			pb.addSource(image);
			pb.add(kernel);

			image = JAI.create("dilate", pb);
		}
		PlanarImageWrapper wrapper = new PlanarImageWrapper(image);
		bufferOuts.add(wrapper);
	}
	
	public void setKernelData(float[] kernel) {
		this.kernelData = kernel;
	}
	
	public void setRepetitions(int repetitions) {
		this.repetitions = repetitions;
	}

}
