package impl.textProcessing.line.alignmentFinisher;


import impl.textProcessing.line.Line;
import impl.textProcessing.word.Word;

public class RightAlignmentFinisher extends AlignmentFinisher {

	public RightAlignmentFinisher(Line line) {
		super(line);
	}

	@Override
	public String finish() {
		StringBuilder sb = new StringBuilder();
		
		boolean init = false;
		
		for(int i = line.getWords().size() - 1; i >= 0; i--) {			
			Word current = line.getWords().get(i);
			if (!init) {
				if(!current.isWhitespace()) {
					sb.insert(0, current.toString());
					init = true;
				} 				
			} else {
				sb.insert(0, current.toString());
			}
		}
		
		int blanksCount = line.getLineLength() - sb.toString().length();
		
		sb.insert(0, getBlanks(blanksCount));
		return sb.toString();
	}

}
