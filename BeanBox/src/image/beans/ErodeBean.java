package image.beans;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;

import image.events.PlanarImageEvent;

public class ErodeBean extends AbstractImageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void handleImageEvent(PlanarImageEvent event) {
		PlanarImage temp = null;
		for(int i = 0; i < repetitions; i++) {
			KernelJAI kernel = new KernelJAI(3, 3, kernelData);
			
			ParameterBlock pb = new ParameterBlock();
			pb.addSource(inImageBuffer);
			pb.add(kernel);
			
			temp = JAI.create("erode", pb);
		}
		outImageBuffer = temp;
	}
	
	public int getRepetitions() {
		return repetitions;
	}


	public void setRepetitions(int repetitions) {
		int old = this.repetitions;
		this.repetitions = repetitions;
		sup.firePropertyChange("repetitions", old, repetitions);
	}

	private float[] kernelData = { 0, 1, 0, 
								   1, 1, 1, 
								   0, 1, 0 }; // default kernel
	
	
	private int repetitions = 1;

}
