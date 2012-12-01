package framework;

/**
 * this interface is used to connect the pipeline
 * 
 * @author Chris
 *
 * @param <in>
 */
public interface Writeable<in> {

	public void write(in data);
	
}
