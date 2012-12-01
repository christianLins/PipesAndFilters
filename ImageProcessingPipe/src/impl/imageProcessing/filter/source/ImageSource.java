package impl.imageProcessing.filter.source;

import impl.imageProcessing.wrapper.PlanarImageWrapper;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import framework.filters.source.AbstractSource;

/**
 * the image source which loads an image from file
 * 
 * @author Chris
 *
 */
public class ImageSource extends AbstractSource<PlanarImageWrapper> {
	
	private String path;
	private PlanarImage image;

	public ImageSource(String path) {
		this.path = path;
	}
	
	public PlanarImage getImage() {
		if(image != null) return image;
		image = JAI.create("fileload", path);
		return image;
	}

	/**
	 * only for pipe operations!
	 * to get the origin image use getImage()
	 */
	@Override
	public PlanarImageWrapper getSource() {
		PlanarImageWrapper planarImageWrapper = new PlanarImageWrapper(getImage());
		buffer.add(planarImageWrapper);
		isEOF = true;
		return planarImageWrapper;
	}

}
