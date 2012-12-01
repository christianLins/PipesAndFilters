package framework.filters;

import framework.PipelineDataObject;


/**
 * This filter is active (pull and push)
 * a runnable filter wraps a common filter to make it active
 * 
 * @author Chris
 *
 */
public class RunnableFilter<in extends PipelineDataObject<in>,out extends PipelineDataObject<out>> implements Filter<in,out>, Runnable {
	
	/**
	 * to stop pulling/pushing set active to false
	 */
	protected boolean active = true;
	
	private AbstractFilter<in, out> filter;

	public RunnableFilter(AbstractFilter<in,out> filter) {
		if (filter == null) throw new NullPointerException();
		this.filter = filter;
	}

	@Override
	public void run() {
		while(!filter.isEof()) {
			out data = filter.read();	
			filter.sendData(data);
		}
	}
	
	public out read() {
		return filter.read();
	}
	
	public void write(in data) {
		filter.write(data);
	}

	@Override
	public void filter(in data) {
		filter.filter(data);		
	}


}
