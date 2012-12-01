package impl.textProcessing.line;

import framework.PipelineDataObject;
import impl.textProcessing.letter.Letter;
import impl.textProcessing.line.LineFilter.Alignment;
import impl.textProcessing.line.alignmentFinisher.AlignmentFinisher;
import impl.textProcessing.line.alignmentFinisher.BlockAlignmentFinisher;
import impl.textProcessing.line.alignmentFinisher.CenterAlignmentFinisher;
import impl.textProcessing.line.alignmentFinisher.LeftAlignmentFinisher;
import impl.textProcessing.line.alignmentFinisher.RightAlignmentFinisher;
import impl.textProcessing.word.Word;
import impl.textProcessing.word.splitter.WordSplitException;
import impl.textProcessing.word.splitter.WordSplitter;

import java.util.LinkedList;
import java.util.List;

public class Line implements PipelineDataObject<Line> {
	
	private final int lineLength;
	private AlignmentFinisher finisher = new LeftAlignmentFinisher(this);
	private List<Word> words = new LinkedList<>();
	private Alignment alignment;
	private WordSplitter splitter = null;
	
	
	public Line(int lineLength, Alignment alignment) {
		this.lineLength = lineLength;
		this.alignment = alignment;
		setAlignment();
	}
	
	/**
	 * 
	 * @param word
	 * @return
	 * @throws WordSplitException 
	 */
	public boolean addWord(Word word) throws WordSplitException {
		if (word == null) return false;
		if (finisher.getRawLineLength() + word.getLength() < lineLength) {
			words.add(word);
			return true;
		} else {
			if (splitter != null) {
				// check for word-split; if possible throw word-split-exception and return remaining part of word by exception
				List<Word> split = splitter.split(word);
				if(split == null) return false;
				
				Word subword1 = new Word();
				
				int remainingLength = lineLength - finisher.getRawLineLength();
				remainingLength--; // decrease for seperator
				
				for(int i = 0; i < split.size(); i++) {
					if (subword1.getLength() + split.get(i).getLength() < remainingLength) {
						subword1.addWord(split.get(i));
					} else {
						if(subword1.getLength() != 0) {
							subword1.addLetter(new Letter('-'));
							words.add(subword1);
							throw new WordSplitException(subword1, word);
						}
					}
				}
			}
		}
		return false;
		
	}
	
		

	public String getContent() {
		return finisher.finish();
	}

	
	public void setWordSplitter(WordSplitter splitter) {
		this.splitter = splitter;
	}
	
	
	
	private void setAlignment() {
		switch (alignment) {
		case left:
			finisher = new LeftAlignmentFinisher(this);
			break;
		case right:
			finisher = new RightAlignmentFinisher(this);
			break;
		case center:
			finisher = new CenterAlignmentFinisher(this);
			break;
		case block:
			finisher = new BlockAlignmentFinisher(this);
			break;
		default:
			break;
		}
	}

	public int getLineLength() {
		return lineLength;
	}

	
	
	@Override
	public String toString() {
		return getContent() + "\r\n";
	}

	public List<Word> getWords() {
		return words;		
	}
	
	private void setWords(List<Word> words) {
		this.words = words;
	}
	
	@Override
	public Line clone() throws CloneNotSupportedException {
		Line l = new Line(lineLength, alignment);
		l.setWords(words);
		return l;
	}

	public boolean isWordSplit() {
		return splitter == null;
	}

	
}
