package image.events;

import java.util.EventObject;

/**
 * 
 * @author Chris
 *
 * @param <eventType> "data object"
 */
public abstract class ImageEvent<eventType> extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected eventType eventType;

	/**
	 * 
	 * @param source sender/creater of event
	 * @param type event type
	 */
	public ImageEvent(Object source, eventType type) {
		super(source);
		this.eventType = type;
	}

	public eventType getData() {
		return eventType;
	}

	

}
