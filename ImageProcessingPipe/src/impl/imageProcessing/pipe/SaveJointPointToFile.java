package impl.imageProcessing.pipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import impl.imageProcessing.wrapper.JointPointSegment;
import framework.pipes.AbstractPipe;

public class SaveJointPointToFile extends AbstractPipe<JointPointSegment> {
	
	private String path;
	

	private int counter = 0;

	public SaveJointPointToFile(String path) {
		this.path = path;
	}

	@Override
	protected void pipeOperation(JointPointSegment data) {
		if (path==null)return;
		File f = new File(path + counter++ + ".rtf" );
		FileWriter writer = null;
		try {
			writer = new FileWriter(f);
			writer.append("There was a point detected in image segment with coordinates " + data.getRelativeSegement().toString() + "\n\r");
			writer.append("The joint point is exactely on the relative position " + data.getRelativePoint() + "\n\r");
			writer.append("The absolute position is " + data.getAbsolutePoint().toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
