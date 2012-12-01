package impl.textProcessing.letter;

import java.util.HashSet;
import java.util.Set;

import framework.PipelineDataObject;

/**
 * simple char for pipeline
 * 
 * @author Chris
 *
 */
public class Letter implements PipelineDataObject<Letter> {
	
	private static Set<Character> VOWELS = new HashSet<>();
	{
		VOWELS.add('a');
		VOWELS.add('e');
		VOWELS.add('i');
		VOWELS.add('o');
		VOWELS.add('u');
		VOWELS.add('ä');
		VOWELS.add('ö');
		VOWELS.add('ü');		
	}
	
	private char c;
	
	public Letter(char c) {
		this.c = c;
	}
	
	@Override
	public Letter clone() throws CloneNotSupportedException {
		return new Letter(c);
	}

	public char getValue() {
		return c;
	}
	
	public boolean isVowel() {
		return VOWELS.contains(getValue());
	}

	public boolean isWhitespace() {
		return c == ' ';
	}

}
