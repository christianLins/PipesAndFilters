package impl.imageProcessing;

import framework.filters.sink.RunnableSink;
import impl.imageProcessing.filter.CalculateCenterFilter;
import impl.imageProcessing.filter.DilateFilter;
import impl.imageProcessing.filter.ErodeFilter;
import impl.imageProcessing.filter.MedianFilter;
import impl.imageProcessing.filter.SegmentToWrapFilter;
import impl.imageProcessing.filter.SegmentationFilter;
import impl.imageProcessing.filter.ThresholdFilter;
import impl.imageProcessing.filter.sink.ImageSink;
import impl.imageProcessing.filter.source.ImageSource;
import impl.imageProcessing.pipe.SaveJointPointToFile;
import impl.imageProcessing.wrapper.JointPointSegment;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;

public class ImagePipeMainPullMode {

	/**
	 * image pipe in pull mode
	 * 
	 * @param args path to save center of joints
	 */
	public static void main(String[] args) {
		String pointSavePath = "./outputs/centers/";
		if(args.length > 0) {
			pointSavePath = args[0];
		}
		
		ImageSource source = new ImageSource("./images/loetstellen.jpg");
		
		RunnableSink<JointPointSegment> runnableSink = new RunnableSink<>(new ImageSink(source.getImage()));
		
		int offset = 50;
		
		int nrSegments = 8;
		int width = 500;
		int height = 50;
		int segmentsWidth = width / nrSegments;
		
		SaveJointPointToFile savePointPipe = new SaveJointPointToFile(pointSavePath);		
		runnableSink.addInput(savePointPipe);
		
		CalculateCenterFilter calculateCenterFilter = new CalculateCenterFilter();		
		calculateCenterFilter.setOffsetTop(offset);
		
		savePointPipe.addInput(calculateCenterFilter);
		
		Collection<Rectangle> segments = new LinkedList<>();
		
		/**
		 * split the inspection segment into subsegments (joint)
		 */
		for(int i = 0; i < nrSegments-1; i++) {
			segments.add(new Rectangle(segmentsWidth * i, 0, segmentsWidth, height));
		}
		
		
		
		SegmentationFilter createPointSegments = new SegmentationFilter(segments);	
		calculateCenterFilter.addInput(createPointSegments);
		
		ThresholdFilter thresholdFilter3 = new ThresholdFilter(251, 255, 255);
		createPointSegments.addInput(thresholdFilter3);
		
		
		ThresholdFilter thresholdFilter2 = new ThresholdFilter(0, 250, 0);
		thresholdFilter3.addInput(thresholdFilter2);
		
		MedianFilter medianFilter2 = new MedianFilter(10);
		thresholdFilter2.addInput(medianFilter2);
		
		DilateFilter dilateFilter = new DilateFilter();
		dilateFilter.setRepetitions(3);
		medianFilter2.addInput(dilateFilter);
		
		ErodeFilter erodeFilter = new ErodeFilter();
		erodeFilter.setRepetitions(5);
		dilateFilter.addInput(erodeFilter);
		
		MedianFilter medianFilter = new MedianFilter(7);
		erodeFilter.addInput(medianFilter);
		
		ThresholdFilter thresholdFilter = new ThresholdFilter(0, 40, 255);
		medianFilter.addInput(thresholdFilter);
		
		SegmentToWrapFilter segToWrap = new SegmentToWrapFilter();
		thresholdFilter.addInput(segToWrap);
	
		SegmentationFilter segmentationFilter = new SegmentationFilter(new Rectangle(0, offset, 500, 50));
		segToWrap.addInput(segmentationFilter);
		
		
		segmentationFilter.addInput(source);
		
		new Thread(runnableSink).start();
		
	}

}
