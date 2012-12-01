package impl.textProcessing.line.alignmentFinisher;

import impl.textProcessing.line.Line;
import impl.textProcessing.word.Word;

public class LeftAlignmentFinisher extends AlignmentFinisher {

	public LeftAlignmentFinisher(Line line) {
		super(line);
	}

	@Override
	public String finish() {
		String s = getRawContentWithoutInitWhitespace();
		s += getBlanks(line.getLineLength() - s.length());
		return s;

	}

	
	private String getRawContentWithoutInitWhitespace() {
		StringBuilder sb = new StringBuilder();
		boolean init = false;
		for(Word word: line.getWords()) {
			if (!init) {
				if(!word.isWhitespace()) {
					sb.append(word.toString());
					init = true;
				} 				
			} else {
				sb.append(word.toString());
			}
		}
		return sb.toString();
	}
	

}
