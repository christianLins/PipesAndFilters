package impl.imageProcessing.pipe;

import impl.imageProcessing.wrapper.PlanarImageWrapper;
import framework.pipes.AbstractPipe;

public class ShowImagePipe extends AbstractPipe<PlanarImageWrapper> {

	@Override
	protected void pipeOperation(PlanarImageWrapper data) {
		data.showImage();		
	}

}
