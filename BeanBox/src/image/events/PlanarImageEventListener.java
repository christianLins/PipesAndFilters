package image.events;

import java.util.EventListener;

public interface PlanarImageEventListener extends EventListener {
	
	public void handlePlanarImageEvent(PlanarImageEvent event);
	
//	public void handleImageEvent(PlanarImageWrapperEvent event);
//	
//	public void handleImageEvent(PlanarImageSegmentEvent event);
//	
//	public void handleImageEvent(JointPointSegmentEvent event);
	
	
	

}
