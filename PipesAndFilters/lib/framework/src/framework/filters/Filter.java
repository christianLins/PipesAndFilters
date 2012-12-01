package framework.filters;

import framework.PipelineDataObject;
import framework.Readable;
import framework.Writeable;

public interface Filter<in extends PipelineDataObject<in>, out extends PipelineDataObject<out>> extends MinimalFilter<in>, Readable<out>, Writeable<in>{
		
	
}
