package impl.textProcessing.word;

import impl.textProcessing.letter.Letter;
import framework.filters.AbstractFilter;

public class WordFilter extends AbstractFilter<Letter, Word> {

	private Word currentWord;
	
	
		
	@Override
	public void filter(Letter data) {
		if (data == null) return;
		if (currentWord == null) currentWord = new Word();
		
		if (!currentWord.addLetter(data)) {
			bufferOuts.add(currentWord);
			currentWord = new Word();
			currentWord.addLetter(data);
		}
		
	}

}
