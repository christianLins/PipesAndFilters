package framework.filters.source;

import framework.PipelineDataObject;
import framework.Readable;

public interface Source<out extends PipelineDataObject<out>> extends Readable<out> {
	
	public out getSource();
	

}
