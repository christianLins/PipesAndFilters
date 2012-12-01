package impl.textProcessing.word.splitter;

import java.util.List;

import impl.textProcessing.word.Word;

/**
 * implementations of the splitter should split a word by a specified rule
 * 
 * @author Chris
 *
 */
public interface WordSplitter {
	
	/**
	 * returns a list of subwords
	 * 
	 * @param word
	 * @return
	 */
	public List<Word> split(Word word);

}
