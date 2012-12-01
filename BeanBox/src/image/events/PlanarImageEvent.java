package image.events;

import javax.media.jai.PlanarImage;
import javax.sound.sampled.AudioFileFormat.Type;

public class PlanarImageEvent extends ImageEvent<PlanarImage> implements Cloneable {

	public PlanarImageEvent(Object source, PlanarImage type) {
		super(source, type);
	}
	
	public PlanarImageEvent clone() {
		PlanarImageEvent ev  = new PlanarImageEvent(source, eventType.createSnapshot());
		return ev;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
