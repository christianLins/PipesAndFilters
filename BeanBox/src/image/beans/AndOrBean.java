package image.beans;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import image.events.PlanarImageEvent;

public class AndOrBean extends ImageBean {
	
	public enum Function {AND, OR};
	private Function function;
	
	private PlanarImage imageBufferOne;
	private PlanarImage imageBufferTwo;
	
	public AndOrBean() {
		setFunction(Function.AND);
	}

	@Override
	public void handleImageEvent(PlanarImageEvent event) {
		if(imageBufferOne == null) {
			imageBufferOne = event.getData();
		} else {
			imageBufferTwo = imageBufferOne;
			imageBufferOne = event.getData();
		}
		
		if (function.equals(Function.AND)) {
			outImageBuffer = JAI.create("and", imageBufferTwo, imageBufferOne);
		} else {
			outImageBuffer = JAI.create("or", imageBufferTwo, imageBufferOne);
		}
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		Function old = this.function;
		this.function = function;
		sup.firePropertyChange("function", old, function);
	}

}
