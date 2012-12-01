package impl.textProcessing.line.alignmentFinisher;

import impl.textProcessing.line.Line;
import impl.textProcessing.word.Word;

public abstract class AlignmentFinisher {
	
	protected Line line;

	public AlignmentFinisher(Line line) {
		this.line = line;
	}
	
	public abstract String finish();
	
	public int getRawLineLength() {
		return getRawContent().length();
	}
	
	public String getRawContent() {
		StringBuilder sb = new StringBuilder();
		for(Word word: line.getWords()) {
			sb.append(word.toString());
		}
		return sb.toString();
	}
	
	protected int getFreeSpace() {
		return line.getLineLength() - getRawLineLength();
	}

	protected String getBlanks(int freeSpace) {
		String blanks = "";
		for(int i = 0; i < freeSpace; i++) {
			blanks += " ";
		}
		return blanks;
	}
	
}
