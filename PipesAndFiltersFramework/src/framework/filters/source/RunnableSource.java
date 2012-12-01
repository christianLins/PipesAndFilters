package framework.filters.source;

import framework.PipelineDataObject;
import framework.Writeable;

/**
 * a runnable source wraps a common source to make it active
 * 
 * @author Chris
 *
 * @param <out>
 */
public final class RunnableSource<out extends PipelineDataObject<out>> implements Runnable, Source<out> {
	
	private AbstractSource<out> source;

	public RunnableSource(AbstractSource<out> source) {
		this.source = source;
		
	}
	
	public void run() {			
		getSource();
		while(source.buffer.size() > 0) {
			sendData(source.buffer.poll());	
		}
		System.out.println("EOF");
	}
	
	@Override
	public out getSource() {
		return source.getSource();
	}
	
	public void addOutput(Writeable<out> out) {
		source.addOutput(out);
	}
	
	protected void sendData(out data) {
		for(Writeable<out> out: source.outputs) {
			try {
				out.write(data.clone());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public out read() {
		return source.read();
	}

}
