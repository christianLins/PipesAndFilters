package impl.imageProcessing.wrapper;

import java.awt.Point;
import java.awt.Rectangle;

import framework.PipelineDataObject;

public class JointPointSegment implements PipelineDataObject<JointPointSegment> {
	
	private Point point;
	private Rectangle segment;
	private float top;
	private float left;
	

	/**
	 * 
	 * @param point the center point of a joint
	 * @param segment the position of this segment relative to the last segmentation
	 */
	public JointPointSegment(Point point, Rectangle segment) {
		this.point = point;
		this.segment = segment;
	}
	
	@Override
	public JointPointSegment clone() throws CloneNotSupportedException {
		JointPointSegment jointPointSegment = new JointPointSegment(point, segment);
		jointPointSegment.setOffsetLeft(left);
		jointPointSegment.setOffsetTop(top);
		return jointPointSegment;
	}
	
	public Point getRelativePoint() {
		return point;
	}
	
	public Point getAbsolutePoint() {
		int x = (int) (segment.getX() + point.getX() + left);
		int y = (int) (segment.getY() + point.getY() + top);
		return new Point(x,y);
	}
	
	/**
	 * the position of this segment relative to the last segmentation
	 * 
	 * @return
	 */
	public Rectangle getRelativeSegement() {
		return segment;
	}

	/**
	 * the top offset from the segment to the origin image
	 * 
	 * @param top
	 */
	public void setOffsetTop(float top) {
		this.top = top;
	}

	/**
	 * the left offset from the segment to the origin image
	 * 
	 * @param left
	 */
	public void setOffsetLeft(float left) {
		this.left = left;
	}
	

}
