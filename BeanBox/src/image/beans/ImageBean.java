package image.beans;

import image.events.PlanarImageEventListener;
import image.events.PlanarImageEvent;

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
 * scale image view
 * refactoring
 * 
 * uml
 * manual
 */


public abstract class ImageBean extends Canvas implements Serializable, PlanarImageEventListener, PropertyChangeListener {

	
	protected PlanarImage inImageBuffer;
	protected PlanarImage outImageBuffer;
	
	protected PropertyChangeSupport sup = new PropertyChangeSupport(this);
	
	public ImageBean() {
		setSize(200,100);
	    setBackground(Color.BLUE);
	    setName(this.getClass().getName());
	    addPropertyChangeListener(this);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<PlanarImageEventListener> listeners = new LinkedList<>();
	

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
			temp.addAll(listeners);
		}
		for (PlanarImageEventListener listener : temp) {
			listener.handlePlanarImageEvent(event.clone());
			System.out.println("inform listener about change " + event);
		}
	}
	
	@Override
	public void paint(Graphics graphics) {
		if(outImageBuffer == null) {
			System.out.println(getClass() + " No Image in buffer");
			setForeground(Color.RED);
			setBackground(Color.RED);
			return;
		}
		Graphics2D g = (Graphics2D) graphics;
		
		AffineTransform transe = g.getTransform();
		transe.scale(1, 1);
		// faktor
		
		g.drawRenderedImage(outImageBuffer, transe);
				
		setBackground(Color.GRAY);
		
		System.out.println(getClass() + " Paint image");
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Property change in " + getClass().getName());
		handlePlanarImageEvent(new PlanarImageEvent(this, inImageBuffer));
		
	}
	
	@Override
	public final void handlePlanarImageEvent(PlanarImageEvent event) {
		inImageBuffer = event.getData();
		
		if(inImageBuffer != null){
			handleImageEvent(event);	
			firePlanarImageEvent(new PlanarImageEvent(this, outImageBuffer));
		}
		
		repaint();
	}


	protected abstract void handleImageEvent(PlanarImageEvent event);
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		sup.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		sup.removePropertyChangeListener(listener);
	}
	
	
	
}
