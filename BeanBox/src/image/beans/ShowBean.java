package image.beans;

import image.events.PlanarImageEvent;

public class ShowBean extends ImageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	public void handleImageEvent(PlanarImageEvent event) {
		this.outImageBuffer = event.getData();
	}


	

}
