package framework.filters.sink;

import framework.PipelineDataObject;
import framework.filters.MinimalFilter;



public interface Sink<in extends PipelineDataObject<in>> extends MinimalFilter<in> {
	
	

}
