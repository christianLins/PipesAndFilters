package impl.textProcessing.line;


import impl.textProcessing.word.Word;
import impl.textProcessing.word.splitter.WordSplitException;
import impl.textProcessing.word.splitter.WordSplitter;
import framework.filters.AbstractFilter;

public class LineFilter extends AbstractFilter<Word, Line> {
	
	private int lineLength = 40;
	private Alignment alignment = Alignment.left;
	private WordSplitter splitter = null;
	
	private Line currentLine;
	
	
	public LineFilter() {
	}
	

	

	public int getLineLength() {
		return lineLength;
	}



	public void setLineLength(int lineLength) {
		this.lineLength = lineLength;
	}

	public Alignment getAlignment() {
		return alignment;
	}



	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	

		
	public enum Alignment {
		left, right, center, block;
	}

	@Override
	public void filter(Word data) {
		if (currentLine == null) currentLine = getNewLine();	
		
		try {
			if (!currentLine.addWord(data)) {
				bufferOuts.add(currentLine);
				currentLine = getNewLine();
				currentLine.addWord(data);
			}
		} catch (WordSplitException e) {
			bufferOuts.add(currentLine);
			currentLine = getNewLine();
			try {
				currentLine.addWord(e.getRemainingWord());
			} catch (WordSplitException e1) {
				// should be possible - otherwise increase linelength
				// ignore
			}
		}
		
		
	}

	public void setWordSplitter(WordSplitter splitter) {
		this.splitter = splitter;
	}



	private Line getNewLine() {
		Line l = new Line(lineLength, alignment);
		l.setWordSplitter(splitter);
		return l;
	}
	
}
