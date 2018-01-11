package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import java.util.concurrent.Callable;

//implements callable interface to allow multithreading of documents
/*
 * Callable is same as Runnable but it can return any type of Object if we want to get a result or status from work (callable).
 */
public class Worker implements Callable<Document> {
	private int docId;
	private File file;
	private int shingleSize;
	public Worker(int docId, File file, int shingleSize) {
		this.docId = docId;
		this.file = file;
		this.shingleSize = shingleSize;
	}
	
	//adapted from https://dzone.com/articles/java-callable-future-understanding
	
	//overriding implement methods
	@Override
	public Document call() throws Exception {
		ShingleParser parser = new ShingleParser(shingleSize);
		Set<Integer> map = parser.parse(new FileInputStream(file));
		Document document = new Document(docId, map);
		return document;
	}

}

