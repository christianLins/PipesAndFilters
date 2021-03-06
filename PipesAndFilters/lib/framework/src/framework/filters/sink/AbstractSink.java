package framework.filters.sink;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import framework.PipelineDataObject;
import framework.Writeable;

/**
 * this abstract class supports basic functionality for a sink
 * extend this class for your own sink
 * 
 * @author Chris
 *
 * @param <in>
 */
public abstract class AbstractSink<in extends PipelineDataObject<in>> implements Writeable<in>, Sink<in> {

	
	protected Queue<in> bufferIns = new LinkedBlockingQueue<>();
	
	@Override
	public void write(in data) {
		filter(data);		
	}
	
	

}
