package impl.imageProcessing.filter;

import impl.imageProcessing.wrapper.JointPointSegment;
import impl.imageProcessing.wrapper.PlanarImageSegment;

import java.awt.Point;
import java.awt.image.Raster;
import java.util.LinkedList;
import java.util.List;

import framework.filters.AbstractFilter;

/**
 * this class processes the joint point center from an image segment
 * for additional informations (relativ position to the whole image etc.) use the offsets left and top
 * 
 * @author Chris
 *
 */
public class CalculateCenterFilter extends
		AbstractFilter<PlanarImageSegment, JointPointSegment> {
	
	private List<Point> rawPoints = new LinkedList<>();
	private float top = 0;
	private float left = 0;

	@Override
	public void filter(PlanarImageSegment data) {
		Raster raster = data.getPlanarImageWrapper().getImage().getData();
		
		for(int x = 0; x < raster.getWidth(); x++) {
			for(int y = 0; y < raster.getHeight(); y++) {
				int sample = raster.getSample(x, y, 0);
				if(sample == 255) {
					setPoint(x, y);
				}
			}
		}
		Point calculateCenter = calculateCenter();
		JointPointSegment jointPointSegment = new JointPointSegment(calculateCenter, data.getSegment());
		jointPointSegment.setOffsetTop(top);
		jointPointSegment.setOffsetLeft(left);
		bufferOuts.add(jointPointSegment);
	
	}

	private Point calculateCenter() {
		double sumX = 0;
		double sumY = 0;
		
		for(Point p: rawPoints) {
			sumX += p.getX();
			sumY += p.getY();
		}
		return new Point((int)(sumX / rawPoints.size()), (int)(sumY / rawPoints.size()));
	}

	private void setPoint(int x, int y) {
		rawPoints.add(new Point(x,y));		
	}

	public void setOffsetTop(float top) {
		this.top = top;
	}
	
	public void setOffsetLeft(float left) {
		this.left = left;
	}
	
	

}
