package framework;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * this abstract class is used for filters/pipes for the basic communication
 * ! source and sink are special, because the have not both - in- and outputs 
 * 
 * @author Chris
 *
 * @param <in>
 * @param <out>
 */
public abstract class AbstractIOPipelineComponent<in extends PipelineDataObject<in>,out extends PipelineDataObject<out>> implements Readable<out>, Writeable<in> {
	
	protected Collection<Readable<in>> inputs = new LinkedList<Readable<in>>();
	protected Collection<Writeable<out>> outputs = new LinkedList<Writeable<out>>();
	
	protected Queue<out> bufferOuts = new LinkedBlockingQueue<>();
	protected Queue<in> bufferIns = new LinkedBlockingQueue<>();
	
	protected boolean eof = false;
	
	protected void readData() {
		for(Readable<in> element: inputs) {
			in input = element.read();
			if (input != null) {
				bufferIns.add(input);
			} else {
				setEof(true);
			}
		}
	}
	

	protected void setEof(boolean b) {
		eof = b;		
	}


	public void sendData(out data) {
		for(Writeable<out> out: outputs) {
			try {
				out.write(data.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public synchronized void addOutput(Writeable<out> out) {
		outputs.add(out);
	}
	
	public synchronized void removeOutput(Writeable<out> out) {
		outputs.remove(out);
	}
	
	
	public synchronized void addInput(Readable<in> in) {
		inputs.add(in);
	}
	
	public synchronized void removeInput(Readable<in> in) {
		inputs.remove(in);
	}


	public boolean isEof() {
		return eof;
	}


}
