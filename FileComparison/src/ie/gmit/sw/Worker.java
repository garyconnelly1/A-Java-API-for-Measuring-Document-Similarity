package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;
import java.util.concurrent.Callable;


/*
 * Implements callable interface to allow multithreading of documents
 * Callable is same as Runnable but it can return any type of Object if we
 *  want to get a result or status from work (callable).
 *  Create Thread constructor to take 3 parameters, int, File and int.
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
	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 * Call method responsible for returning a Document object.
	 * Gives application the ability for multi - threading.
	 * {@author Gary Connelly}
	 */
	
	//overriding implement methods
	@Override
	public Document call() throws Exception {
		ShingleParser parser = new ShingleParser(shingleSize);
		Set<Integer> map = parser.parse(new FileInputStream(file));
		Document document = new Document(docId, map);
		return document;
	}
	//for uml http://www.objectaid.com/installation

}

