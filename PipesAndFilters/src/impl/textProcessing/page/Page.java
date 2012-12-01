package impl.textProcessing.page;

import framework.PipelineDataObject;
import impl.textProcessing.line.Line;

import java.util.LinkedList;
import java.util.List;

public class Page implements PipelineDataObject<Page> {
	
	private int pageLength;
	private List<Line> lines = new LinkedList<Line>();
	
	public Page(int pageLength) {
		this.pageLength = pageLength;
	
	}
	
	public boolean addLine(Line line) {
		if (!(lines.size() + 1 > pageLength)) {
			lines.add(line);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Line l:lines) {
			sb.append(l.toString());
		}
		return sb.toString() + "\r\n";
	}
	
	
	
	public List<Line> getLines() {
		return lines;
	}

	private void setLines(List<Line> lines) {
		this.lines = lines;
	}

	@Override
	public Page clone() throws CloneNotSupportedException {
		Page p = new Page(pageLength);
		p.setLines(getLines());
		return p;
	}
	
}
