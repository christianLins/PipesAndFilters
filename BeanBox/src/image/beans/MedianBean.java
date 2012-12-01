package image.beans;

import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.MedianFilterDescriptor;

import image.events.PlanarImageEvent;
import impl.imageProcessing.wrapper.PlanarImageWrapper;

public class MedianBean extends ImageBean{

	private int intensity;
	
	public enum Mask {SQUARE, SQUARE_SEPERABLE, PLUS, X}
	private Mask mask;
	
	public Mask getMask() {
		return mask;
	}


	public void setMask(Mask mask) {
		Mask old = this.mask;
		this.mask = mask;
		sup.firePropertyChange("mask", old, mask);
	}


	public MedianBean() {
		this.intensity = 1;
		this.mask = Mask.SQUARE;
	}


	@Override
	public void handleImageEvent(PlanarImageEvent event) {
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(event.getData());
		
		switch (mask) {
		case SQUARE:
			pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
			break;
		case SQUARE_SEPERABLE:
			pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE_SEPARABLE);
			break;
		case PLUS:
			pb.add(MedianFilterDescriptor.MEDIAN_MASK_PLUS);
			break;
		case X:
			pb.add(MedianFilterDescriptor.MEDIAN_MASK_X);
			break;			
		default:
			pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
			break;
		}
		
		pb.add(getIntensity());
		RenderedOp result = JAI.create("MedianFilter", pb);		
		
		outImageBuffer = (result);
	}



	public int getIntensity() {
		return intensity;
	}


	public void setIntensity(int intensity) {
		int old = this.intensity;
		this.intensity = intensity;
		sup.firePropertyChange("intensity", old, intensity);
	}

}
