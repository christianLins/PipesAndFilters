package impl.imageProcessing.pipe;

import impl.imageProcessing.wrapper.PlanarImageWrapper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import framework.pipes.AbstractPipe;

public class SavePlanarImageWrapperToFilePipe extends AbstractPipe<PlanarImageWrapper> {
	
	private String path;

	public SavePlanarImageWrapperToFilePipe(String path) {
		this.path = path;
	}

	@Override
	protected void pipeOperation(PlanarImageWrapper data) {
		PlanarImage image = data.getImage();
		
		try {
			FileOutputStream fo = new FileOutputStream(path);
			JAI.create("encode", image, fo, "PNG", null);
			JAI.create("filestore", image, path, "PNG", null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
