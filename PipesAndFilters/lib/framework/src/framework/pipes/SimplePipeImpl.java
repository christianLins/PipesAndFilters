package framework.pipes;

import framework.PipelineDataObject;


/**
 * this pipe is going to do nothing with the passing data
 * it can be used as splitter or merger
 * 
 * --> connect filter with filter, if you do nothing useful in or with the pipe
 * 
 * @author Chris
 *
 * @param <T>
 */
public final class SimplePipeImpl<T extends PipelineDataObject<T>> extends AbstractPipe<T> {

	@Override
	protected void pipeOperation(T data) {
		// do nothing
	}

}
