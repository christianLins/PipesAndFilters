package framework.filters;

import framework.PipelineDataObject;


/**
 * this interface only supports the filter method, but no concrete
 * filter-design in context of pipes and filters with in- and output
 * for this case use the <code>Filter<code> interface
 * 
 * @author Chris
 *
 * @param <in>
 */
public interface MinimalFilter<in extends PipelineDataObject<in>> {
	
	/**
	 * Filter function
	 * 
	 * @param data
	 * @return <code>true if filter has result<code> 
	 */
	void filter(in data);

}
