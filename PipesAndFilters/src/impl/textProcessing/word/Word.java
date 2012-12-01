package impl.textProcessing.word;

import java.util.LinkedList;
import java.util.List;

import impl.textProcessing.letter.Letter;
import framework.PipelineDataObject;

public class Word implements PipelineDataObject<Word> {
	
	private List<Letter> letters = new LinkedList<>();
	
	public boolean addLetter(Letter letter) {
		char c = letter.getValue();
		if (isAddable(c)) {
			letters.add(letter);
			return true;
		} else {
			return false;
		}
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Letter l : letters) {
			sb.append(l.getValue());
		}
		return sb.toString();
	}

	private boolean isAddable(char letter) {	
		int length = getLength();
		if (letter != ' ') {
			// no blank	
			if (length == 1)  {
				if (letters.get(0).getValue() == ' ') {					
					return false;					
				} 
			} 
			return true;
		} else {
			// blank
			if(letters.size() == 0) {
				return true;
			} else if(letters.get(letters.size() - 1).getValue() == ' ') {
				return true;
			}
			return false;
		}
	}

	public int getLength() {
		return toString().length();
	}
	
	public boolean isWhitespace() {
		for (Letter l : letters) {
			if (!l.isWhitespace()) return false;
		}
		
		return true;
	}
	
	@Override
	public Word clone() throws CloneNotSupportedException {
		Word w = new Word();
		w.setLetters(letters);
		return w;
	}


	private void setLetters(List<Letter> letters) {
		this.letters = letters;
	}
	
	public void addWord(Word word) {
		letters.addAll(word.getLetters());
	}



	public List<? extends Letter> getLetters() {
		return letters;
	}


	/**
	 * trims a number (length) of letters from the word - starting from the beginning
	 * 
	 * @param length
	 * @return new word
	 */
	public Word trimFront(int length) {
		if (letters.size() < length) return null; // no valid input
		
		for(int i = 0; i < length; i++) {
			letters.remove(0);
		}		
		return this;
	}



	public Word getSubWord(int start, Integer end) {
		List<Letter> newLetters = new LinkedList<>();
		Word newWord = new Word();
		
		if (start < 0) start = 0;
		if (end > letters.size()) end = letters.size();
		for(int i = start; i < end; i++) {
			newLetters.add(letters.get(i));
		}
		newWord.setLetters(newLetters);
		return newWord;
	}
	
	

}
