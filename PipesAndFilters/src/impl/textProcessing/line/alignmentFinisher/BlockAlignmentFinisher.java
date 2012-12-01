package impl.textProcessing.line.alignmentFinisher;

import java.util.Iterator;

import impl.textProcessing.line.Line;
import impl.textProcessing.word.Word;

public class BlockAlignmentFinisher extends AlignmentFinisher {

	public BlockAlignmentFinisher(Line line) {
		super(line);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String finish() {
		StringBuilder sb = new StringBuilder(line.getLineLength());
		
		Iterator<Word> it = line.getWords().iterator();
		
		sb.append(it.next());
		
		int divisor = line.getWords().size()-1;
		
		if (divisor == 0) divisor = 1;		
		
		int blanksCount = getFreeSpace()/ (divisor);
		String filler = getBlanks(blanksCount);
		
		while(it.hasNext()) {
			sb.append(filler);
			sb.append(it.next());
		}
		return sb.toString();
	}

}
