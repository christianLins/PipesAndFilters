package framework.filters.sink;

import java.util.Collection;
import java.util.LinkedList;

import framework.PipelineDataObject;
import framework.Readable;

/**
 * a runnable Sink wraps a common sink to make it active
 * 
 * @author Chris
 *
 * @param <in>
 */
public final class RunnableSink<in extends PipelineDataObject<in>> implements Sink<in>, Runnable {
	
	private Collection<Readable<in>> inputs = new LinkedList<Readable<in>>();

	/**
	 * to stop pulling set active to false
	 */
	protected boolean active = true;
	
	private AbstractSink<in> sink;

	public RunnableSink(AbstractSink<in> sink) {
		this.sink = sink;
	}
	

	@Override
	public void run() {
		while (!sink.isEOF()) {
			readData();	
			while(sink.bufferIns.size() > 0) {
				filter(sink.bufferIns.poll());
			}
		}
		sink.doEOFOperation();
	}
	
	public void readData() {
		for(Readable<in> in: inputs) {
			in input = in.read();
			if (input != null) {
				sink.bufferIns.add(input);
			} else {
				sink.setEOF(true);
				System.out.println("End of Stream");
			}
		}
	}


	@Override
	public void filter(in data) {
		sink.filter(data);		
	}
	

	public synchronized void addInput(Readable<in> in) {
		inputs.add(in);
	}
	
	public synchronized void removeInput(Readable<in> in) {
		inputs.remove(in);
	}

}
