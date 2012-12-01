package framework;

/**
 * this interface is used to connect the pipeline
 * 
 * @author Chris
 *
 * @param <out>
 */
public interface Readable<out> {
	
	public out read();

}
