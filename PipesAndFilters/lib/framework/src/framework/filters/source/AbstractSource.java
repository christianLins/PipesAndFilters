package framework.filters.source;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import framework.PipelineDataObject;
import framework.Writeable;

/**
 * this abstract class supports basic functionality for a source
 * extend this class for your own source
 * 
 * @author Chris
 *
 * @param <out>
 */
public abstract class AbstractSource<out extends PipelineDataObject<out>> implements Source<out> {
	
	protected Collection<Writeable<out>> outputs = new LinkedList<Writeable<out>>();
	protected Queue<out> buffer = new LinkedBlockingQueue<>();
	
	
	
	public synchronized void addOutput(Writeable<out> out) {
		outputs.add(out);
	}
	
	public synchronized void removeOutput(Writeable<out> out) {
		outputs.remove(out);
	}

	@Override
	public out read() {
		getSource();		
		if (buffer.isEmpty()) {
			return null; // eof
		} else {
			return buffer.poll();
		}
	}

	
		

}
