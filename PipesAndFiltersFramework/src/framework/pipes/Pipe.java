package framework.pipes;

import framework.PipelineDataObject;
import framework.Readable;
import framework.Writeable;

public interface Pipe<T extends PipelineDataObject<T>> extends Readable<T>, Writeable<T>  {
	
	
}
