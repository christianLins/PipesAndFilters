package impl.textProcessing;

import impl.textProcessing.letter.Letter;
import impl.textProcessing.line.LineFilter;
import impl.textProcessing.line.LineFilter.Alignment;
import impl.textProcessing.page.Page;
import impl.textProcessing.page.PageFilter;
import impl.textProcessing.sink.BookSink2File;
import impl.textProcessing.source.TextSource;
import impl.textProcessing.word.Word;
import impl.textProcessing.word.WordFilter;
import impl.textProcessing.word.splitter.VowelSplitter;
import framework.filters.sink.RunnableSink;
import framework.filters.source.AbstractSource;
import framework.pipes.AbstractPipe;
import framework.pipes.SimplePipeImpl;

public class TextPullPipe {

	/**
	 * test of a pull-pipe
	 * 
	 * @param args
	 */
	public static void main(String[] args) {		
		
		RunnableSink<Page> sink = new RunnableSink<Page>(new BookSink2File("./book/page"));
		
		PageFilter pageFilter = new PageFilter();
		pageFilter.setPageLength(50);
		sink.addInput(pageFilter);
		
		
		LineFilter lineFilter = new LineFilter();
		lineFilter.setLineLength(70);
		lineFilter.setAlignment(Alignment.center); // set the alignment
		lineFilter.setWordSplitter(new VowelSplitter()); // vowel word splitter
		pageFilter.addInput(lineFilter);
				
		WordFilter wordFilter = new WordFilter();
		AbstractPipe<Word> wordPipe = new SimplePipeImpl<>();
		lineFilter.addInput(wordPipe);			
		wordPipe.addInput(wordFilter);
		
		AbstractSource<Letter> source = new TextSource("D:\\Dropbox\\FHV\\Semester_5\\Systemarchitekturen\\Übungen\\source.txt");
		wordFilter.addInput(source);
		
		
		
		// run pipe		
		new Thread(sink).start();

	}

}
