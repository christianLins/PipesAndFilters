package framework.pipes;


import framework.AbstractIOPipelineComponent;
import framework.PipelineDataObject;

public abstract class AbstractPipe<T extends PipelineDataObject<T>> extends AbstractIOPipelineComponent<T, T> implements Pipe<T> {
	
	@Override
	public void write(T data) {
		pipeOperation(data);
		bufferOuts.add(data);
		while(bufferOuts.size() > 0) {
			sendData(bufferOuts.poll());
		}
	}

	@Override
	public T read() {
		readData();
		while (bufferIns.size() > 0) {
			T data = bufferIns.poll();
			pipeOperation(data);
			bufferOuts.add(data);
		}
		return bufferOuts.poll();
	}
	
	/**
	 * this method should add the data to the bufferOuts, after work with it
	 * 
	 * @param data
	 */
	protected abstract void pipeOperation(T data);
		
	
}
