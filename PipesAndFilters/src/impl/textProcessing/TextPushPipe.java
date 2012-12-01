package impl.textProcessing;

import impl.textProcessing.letter.Letter;
import impl.textProcessing.line.Line;
import impl.textProcessing.line.LineFilter;
import impl.textProcessing.line.LineFilter.Alignment;
import impl.textProcessing.page.PageFilter;
import impl.textProcessing.sink.BookSink2File;
import impl.textProcessing.source.TextSource;
import impl.textProcessing.word.Word;
import impl.textProcessing.word.WordFilter;
import impl.textProcessing.word.splitter.VowelSplitter;
import framework.filters.source.AbstractSource;
import framework.filters.source.RunnableSource;
import framework.pipes.AbstractPipe;
import framework.pipes.SimplePipeImpl;

public class TextPushPipe {

	/**
	 * test of a push pipe
	 * 
	 * @param args
	 */
	public static void main(String[] args) {		
		// push pipe
		AbstractSource<Letter> source = new TextSource("D:\\Dropbox\\FHV\\Semester_5\\Systemarchitekturen\\Übungen\\source.txt");
		WordFilter wordFilter = new WordFilter();
		
		source.addOutput(wordFilter);
		
		AbstractPipe<Word> wordPipe = new SimplePipeImpl<>();
		wordFilter.addOutput(wordPipe);
		
		LineFilter lineFilter = new LineFilter();
		lineFilter.setLineLength(40);
		lineFilter.setAlignment(Alignment.left);
		lineFilter.setWordSplitter(new VowelSplitter());
		
		wordPipe.addOutput(lineFilter);
		
		SimplePipeImpl<Line> linePipe = new SimplePipeImpl<>();
	
		lineFilter.addOutput(linePipe);
		
		PageFilter pageFilter = new PageFilter();
		pageFilter.setPageLength(50);
		
		linePipe.addOutput(pageFilter);
		
		
		
		BookSink2File sink = new BookSink2File("./book/page");

		pageFilter.addOutput(sink);
		
		// run pipe		
		new Thread(new RunnableSource<Letter>(source)).start();

	}

}
