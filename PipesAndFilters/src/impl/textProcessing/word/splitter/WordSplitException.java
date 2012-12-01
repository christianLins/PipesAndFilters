package impl.textProcessing.word.splitter;

import impl.textProcessing.word.Word;

@SuppressWarnings("serial")
public class WordSplitException extends Exception {
	
	private Word originallyWord;
	private Word remainingWord;
	private Word subword1;

	public WordSplitException(Word subword1, Word originallyWord) {
		super("The word " + originallyWord.toString() + " was split and the first part was " + subword1.toString());
		this.subword1 = subword1;
		this.originallyWord = originallyWord;
		try {
			this.remainingWord = originallyWord.trimFront(subword1.clone().getLength()-1);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Word getRemainingWord() {
		return remainingWord;
	}
	
	public Word getOriginallyWord() {
		return originallyWord;
	}
	
	public Word getSubword() {
		return subword1;
	}
}
