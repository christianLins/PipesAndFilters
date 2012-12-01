package impl.imageProcessing.filter.sink;

import impl.imageProcessing.wrapper.JointPointSegment;
import impl.imageProcessing.wrapper.PlanarImageWrapper;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.LinkedList;

import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedImageAdapter;

import framework.filters.sink.AbstractSink;

/**
 * the image sink receives join points and allows to show the origin image overlaid with the joint points
 * 
 * @author Chris
 *
 */
public class ImageSink extends AbstractSink<JointPointSegment> {
	
	private Collection<JointPointSegment> segments = new LinkedList<>();
	private PlanarImage origin;
	
	private int pointSize = 3;

	
	public ImageSink(PlanarImage origin) {
		this.origin = origin;
	}

	@Override
	public void filter(JointPointSegment data) {
		segments.add(data);
	}

	/**
	 * build and show overlaid image
	 */
	@Override
	protected void doEOFOperation() {
		BufferedImage buff = origin.getAsBufferedImage();
		Graphics graphics = buff.getGraphics();
		for(JointPointSegment seg: segments) {
			graphics.drawRect((int)seg.getAbsolutePoint().getX() - pointSize / 2, (int)seg.getAbsolutePoint().getY() - pointSize / 2,
					pointSize, pointSize);
		}
		graphics.drawImage(buff, 0, 0, buff.getWidth(), buff.getHeight(), null, null);

		RenderedImageAdapter adapter = new RenderedImageAdapter(buff);
		PlanarImageWrapper planarImageWrapper = new PlanarImageWrapper(adapter.createSnapshot());
		planarImageWrapper.showImage();
	}

}
