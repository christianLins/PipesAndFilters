package framework;

/**
 * this interface has to be implemented, if you like to pass objects of
 * a class through the pipeline
 * 
 * @author Chris
 *
 * @param <T> class
 */
public interface PipelineDataObject<T> extends Cloneable {
	
	T clone() throws CloneNotSupportedException;

}
