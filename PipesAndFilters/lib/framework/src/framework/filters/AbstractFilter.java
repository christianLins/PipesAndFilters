package framework.filters;


import framework.AbstractIOPipelineComponent;
import framework.PipelineDataObject;

public abstract class AbstractFilter<in extends PipelineDataObject<in>,out extends PipelineDataObject<out>> extends AbstractIOPipelineComponent<in,out> implements Filter<in, out> {
	
	

	@Override
	public out read() {
		if (bufferOuts.size() > 0) return bufferOuts.poll();
		out result = null;
		while (result == null && !isEof()) {
			readData();
			while (bufferIns.size() > 0) {
				filter(bufferIns.poll());
			}			
			result = bufferOuts.poll();		
		}
		return result;
	}

	


	@Override
	public void write(in data) {
		filter(data);
		while (bufferOuts.size() > 0) {
			sendData(bufferOuts.poll());
		}
	}

}
