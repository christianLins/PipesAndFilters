package impl.imageProcessing;

import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;

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
import impl.imageProcessing.pipe.SavePlanarImageWrapperToFilePipe;
import impl.imageProcessing.wrapper.PlanarImageWrapper;
import framework.filters.source.RunnableSource;

public class ImagePipeMain {
	
	

	/**
	 * Push mode of image pipe
	 * 
	 * points are saved in file
	 * 
	 * @param args path to save center of joints
	 */
	public static void main(String[] args) {
		String pointSavePath = "./outputs/centers/";
		if(args.length > 0) {
			pointSavePath = args[0];
		}
		
		
		// the image pipe logic with image to file pipes
		ImageSource imageSource = new ImageSource("./images/loetstellen.jpg");
		RunnableSource<PlanarImageWrapper> runSource = new RunnableSource<>(imageSource);
		
		SavePlanarImageWrapperToFilePipe saveAfterSource = new SavePlanarImageWrapperToFilePipe("./imagesOutput/afterSource.png");
		runSource.addOutput(saveAfterSource);
		
		int offset = 50;
		
		SegmentationFilter segmentationFilter = new SegmentationFilter(new Rectangle(0, offset, 500, 50));
		saveAfterSource.addOutput(segmentationFilter);
		
		SegmentToWrapFilter segToWrap = new SegmentToWrapFilter();
		segmentationFilter.addOutput(segToWrap);
		
		SavePlanarImageWrapperToFilePipe saveafterSegmentationFilter = new SavePlanarImageWrapperToFilePipe("./imagesOutput/afterSegmentationFilter.png");
		segToWrap.addOutput(saveafterSegmentationFilter);
		
		ThresholdFilter thresholdFilter = new ThresholdFilter(0, 40, 255);
		saveafterSegmentationFilter.addOutput(thresholdFilter);
		
		SavePlanarImageWrapperToFilePipe saveafterThresholdFilter = new SavePlanarImageWrapperToFilePipe("./imagesOutput/afterThresholdFilter.png");
		thresholdFilter.addOutput(saveafterThresholdFilter);
		
		MedianFilter medianFilter = new MedianFilter(7);
		saveafterThresholdFilter.addOutput(medianFilter);
		
		SavePlanarImageWrapperToFilePipe saveafterMedianFilter = new SavePlanarImageWrapperToFilePipe("./imagesOutput/afterMedianFilter.png");
		medianFilter.addOutput(saveafterMedianFilter);
		
		ErodeFilter erodeFilter = new ErodeFilter();
		erodeFilter.setRepetitions(5);
		saveafterMedianFilter.addOutput(erodeFilter);
		
		SavePlanarImageWrapperToFilePipe saveafterErodeFilter = new SavePlanarImageWrapperToFilePipe("./imagesOutput/afterErodeFilter.png");
		erodeFilter.addOutput(saveafterErodeFilter);
		
		DilateFilter dilateFilter = new DilateFilter();
		dilateFilter.setRepetitions(3);
		saveafterErodeFilter.addOutput(dilateFilter);
		
		SavePlanarImageWrapperToFilePipe saveafterDilateFilter = new SavePlanarImageWrapperToFilePipe("./imagesOutput/afterDilateFilter.png");
		dilateFilter.addOutput(saveafterDilateFilter);
		
		MedianFilter medianFilter2 = new MedianFilter(10);
		saveafterDilateFilter.addOutput(medianFilter2);
		
		SavePlanarImageWrapperToFilePipe saveafterMedian2 = new SavePlanarImageWrapperToFilePipe("./imagesOutput/afterMedian2.png");
		medianFilter2.addOutput(saveafterMedian2);
		
		ThresholdFilter thresholdFilter2 = new ThresholdFilter(0, 250, 0);
		saveafterMedian2.addOutput(thresholdFilter2);
		
		ThresholdFilter thresholdFilter3 = new ThresholdFilter(251, 255, 255);
		thresholdFilter2.addOutput(thresholdFilter3);
		
		int nrSegments = 8;
		int width = 500;
		int height = 50;
		int segmentsWidth = width / nrSegments;
		
	
		Collection<Rectangle> segments = new LinkedList<>();
		
		for(int i = 0; i < nrSegments-1; i++) {
			Rectangle rectangle = new Rectangle(segmentsWidth * i, 0, segmentsWidth, height);
			segments.add(rectangle);
		}
		
		SegmentationFilter createPoints = new SegmentationFilter(segments);
		thresholdFilter3.addOutput(createPoints);
		
		CalculateCenterFilter centerFilter = new CalculateCenterFilter();
		centerFilter.setOffsetTop(offset);
		createPoints.addOutput(centerFilter);
		
		SaveJointPointToFile savePointPipe = new SaveJointPointToFile(pointSavePath);
		centerFilter.addOutput(savePointPipe);
		
		ImageSink sink = new ImageSink(imageSource.getImage());
		savePointPipe.addOutput(sink);
				
		
		runSource.run(); // run the pipe

	}

}
