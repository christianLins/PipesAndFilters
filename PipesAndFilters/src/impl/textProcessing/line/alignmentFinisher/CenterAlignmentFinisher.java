package impl.textProcessing.line.alignmentFinisher;

import impl.textProcessing.line.Line;

public class CenterAlignmentFinisher extends AlignmentFinisher {

	public CenterAlignmentFinisher(Line line) {
		super(line);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String finish() {
		int blanksCount = getFreeSpace()/2;
		String s = getBlanks(blanksCount);
		s += getRawContent();
		s += getBlanks(blanksCount);
		return s;
	}

}
