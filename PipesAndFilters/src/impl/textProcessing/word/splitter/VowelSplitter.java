package impl.textProcessing.word.splitter;

import impl.textProcessing.letter.Letter;
import impl.textProcessing.word.Word;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class VowelSplitter implements WordSplitter {
	
	private static final int MIN_SPLIT = 2;
	
	

	@Override
	public List<Word> split(Word word) {
		List<Word> splits = new LinkedList<>(); 

		if(word.getLength() < 4 || word.isWhitespace()) {
			splits.add(word);
			return splits;
		}
		
		List<? extends Letter> letters = word.getLetters();
		List<Integer> pos = new LinkedList<>();
		
		for(int i = 0; i < letters.size(); i++) {
			if(letters.get(i).isVowel()) {
				if(i >= MIN_SPLIT) {
					if (pos.size()-1 < 0) {
						if (word.getLength() - i > MIN_SPLIT) {
							pos.add(i+1);
							i += 2;
							continue;
						}
					} else {
						if (i >= i - pos.get(pos.size() - 1)) {
							if (word.getLength() - i > MIN_SPLIT) {
								pos.add(i+1);
								i += MIN_SPLIT;
								continue;
							}
						}
					}
				}
				
			}
		}
		
		if(pos.size() > 0) {
			Iterator<Integer> iterator = pos.iterator();
			int last = 0;
			while(iterator.hasNext()) {
				int current = iterator.next();
				splits.add(word.getSubWord(last, current));
				last = current;
			}
			if (last != word.getLength()) {
				splits.add(word.getSubWord(last, word.getLength()));
			}
		}		
		return splits;
	}

}
