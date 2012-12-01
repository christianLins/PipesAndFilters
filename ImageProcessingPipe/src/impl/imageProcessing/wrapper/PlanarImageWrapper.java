package impl.imageProcessing.wrapper;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.sun.media.jai.widget.DisplayJAI;

import framework.PipelineDataObject;

/**
 * data objekt to pass image data over the pipe line
 * 
 * @author Chris
 *
 */
public class PlanarImageWrapper implements PipelineDataObject<PlanarImageWrapper> {
	
	private PlanarImage image;

	public PlanarImageWrapper(PlanarImage image) {
		this.image = image;
	}
	
	@Override
	public PlanarImageWrapper clone() throws CloneNotSupportedException {		
		return new PlanarImageWrapper(image.createSnapshot());
	}
	
	public PlanarImage getImage() {
		return image;
	}
	
	/**
	 * show the image
	 */
	public void showImage() {
				 
		// Get some information about the image
		   String imageInfo =
		   "Dimensions: "+image.getWidth()+"x"+image.getHeight()+ " Bands:"+image.getNumBands();

		// Create a frame for display.
		   JFrame frame = new JFrame();
		   frame.setTitle("Planar Image Wrapper Visualizer");

		// Get the JFrame’ ContentPane.
		   Container contentPane = frame.getContentPane();
		   contentPane.setLayout(new BorderLayout());

	
		// Create an instance of DisplayJAI.
		   DisplayJAI dj = new DisplayJAI(image);
		 

		// Add to the JFrame’ ContentPane an instance of JScrollPane
		// containing the DisplayJAI instance.
		   contentPane.add(new JScrollPane(dj),BorderLayout.CENTER);
		 
		// Add a text label with the image information.
		   contentPane.add(new JLabel(imageInfo),BorderLayout.SOUTH);

		// Set the closing operation so the application is finished.   
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   frame.setSize((int)(image.getWidth() * 1.2),(int)(image.getHeight() * 1.2)); // adjust the frame size.
		   frame.setVisible(true); // show the frame.
	}

}
