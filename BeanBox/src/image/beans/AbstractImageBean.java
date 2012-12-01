package image.beans;

import image.events.PlanarImageEvent;
import image.events.PlanarImageEventListener;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.media.jai.PlanarImage;

/*
 * todo:
 * -----
 * descriptor
 * 
 * uml
 * manual
 */

/**
 * the all over image bean logic
 * defines the communication logic
 * and paints the images (outImageBuffer) as canvas
 * 
 * @author Chris
 *
 */
public abstract class AbstractImageBean extends Canvas implements Serializable, PlanarImageEventListener, PropertyChangeListener {

	
	protected PlanarImage inImageBuffer;
	protected PlanarImage outImageBuffer;
	
	protected PropertyChangeSupport sup = new PropertyChangeSupport(this);
	
	/**
	 * init the bean
	 */
	public AbstractImageBean() {
		setSize(200,100);
	    setBackground(Color.BLUE);
	    setName(this.getClass().getName());
	    addPropertyChangeListener(this); // registers the bean itself as propterty change listener
	}
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * all the image event listener
	 */
	private List<PlanarImageEventListener> listeners = new LinkedList<>();
	
	/**
	 * is image changed after filter operation?
	 */
	private boolean change = true;
	

	public void addPlanarImageEventListener(PlanarImageEventListener listener) {
		listeners.add(listener);
		System.out.println("listener added " + listener);
	}

	
	public void removePlanarImageEventListener(PlanarImageEventListener listener) {
		listeners.remove(listener);
	}

	protected synchronized void firePlanarImageEvent(PlanarImageEvent event) {
		System.out.println(event + " fired");
		List<PlanarImageEventListener> temp = new LinkedList<PlanarImageEventListener>();
		synchronized (this) {
			temp.addAll(listeners); // inform all CURRENT listeners about event
		}
		for (PlanarImageEventListener listener : temp) {
			listener.handlePlanarImageEvent(event.clone()); // make a copy
			System.out.println("inform listener about change " + event);
		}
	}
	
	/**
	 * paint the image if it is available and something changed
	 */
	@Override
	public void paint(Graphics graphics) {
		if(outImageBuffer == null || !change) {
			System.out.println(getClass() + " No Image in buffer");
			setForeground(Color.RED);
			setBackground(Color.RED);
			return;
		}
		Graphics2D g = (Graphics2D) graphics;
		
		double x;
		double y;
		
		try {
			x = (double)getWidth() / (double)outImageBuffer.getWidth();
			y = (double)getHeight() / (double)outImageBuffer.getHeight();
		} catch (Exception e) {
			x = 1;
			y = 1;
		}
		
		// scale the image into the canvas
		AffineTransform transe = g.getTransform();
		transe.scale(x, y);
		
		g.drawRenderedImage(outImageBuffer, transe);
				
		setBackground(Color.GRAY);
		
		System.out.println(getClass() + " Paint image");
	}
	
	/**
	 * inform listeners about local change (we are described as property change listeners locally)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Property change in " + getClass().getName());
		handlePlanarImageEvent(new PlanarImageEvent(this, inImageBuffer));
	}
	
	/**
	 * handle a incoming image event
	 */
	@Override
	public final void handlePlanarImageEvent(PlanarImageEvent event) {
		inImageBuffer = event.getData(); // store incoming image
		
		PlanarImage old = outImageBuffer; // save old image to check changes
		
		beforeHandleImageEvent(); // do something before main executing routine
		
		if(inImageBuffer != null){
			handleImageEvent(event);	// main routine
			firePlanarImageEvent(new PlanarImageEvent(this, outImageBuffer));  // forward change event
		}
		
		// check for changes
		if (old == null) {
			change = true;
		} else if (old.equals(outImageBuffer)) {
				this.change = false;
			} else {
				change = true;
		}
		repaint();
	}

	/**
	 * override if necessary
	 * --> main routine is never called if event had no image!!
	 */
	protected void beforeHandleImageEvent() {
	}

	/**
	 * filter method
	 * 
	 * @param event
	 */
	protected abstract void handleImageEvent(PlanarImageEvent event);
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		sup.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		sup.removePropertyChangeListener(listener);
	}
	
	
	
}
