package impl.textProcessing.page;

import impl.textProcessing.line.Line;
import framework.filters.AbstractFilter;

public class PageFilter extends AbstractFilter<Line, Page> {

	private int pageLength = 8;
	private Page currentPage;
	
	public PageFilter() {
	}

	
	@Override
	public void filter(Line data) {
		if (currentPage == null) currentPage = new Page(pageLength);
		
		if (!currentPage.addLine(data)) {
			bufferOuts.add(currentPage);
			currentPage = new Page(pageLength);
			currentPage.addLine(data);
		}
	}
	
	public void setPageLength(int length) {
		this.pageLength = length;
	}

}
