package ie.gmit.sw;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/*
 * Runner class contains the main method and is responsible for starting off the worker threads.
 */
public class Runner {
	private static Scanner scanner = new Scanner(System.in);
	private static File file, file2;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		 * set number of minhashes to 200, and the shingle size to 4.
		 */
		int numberOfHashes = 200;
		int shingleSize = 4;
		
		readFiles();
		/*
		 * ExecutorService allows us to creat a threadPool. Was unaware of this function
		 * prior to doing this project but learned about it 
		 * here -  https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html
		 */
		
		ExecutorService service = Executors.newCachedThreadPool();
		/*
		 * Future<Document> allows us to create an instance of the worker thread.
		 * Submits a value-returning task for execution and returns a Future
		 *  representing the pending results of the task. 
		 *  More about the Future and submit methods can be learned here
		 *  https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Future.html
		 */
	 
		Future<Document> doc1 = service.submit(new Worker(1, file, shingleSize));
		Future<Document> doc2 = service.submit(new Worker(2, file2, shingleSize));

		MinHashJaccardComparer comparer = new MinHashJaccardComparer(numberOfHashes);
		float jaccardIndex = comparer.compare(doc1.get(), doc2.get());
		System.out.printf("The jaccard index for the two documents is %.2f", jaccardIndex);

		scanner.close();
	}

	/*
	 * Void method to read the files. 
	 * Can also validate if the document exists in the system. 
	 * Uses the global variable scanner to read each document and save the, as file and file2.
	 * Will only use the documents if they already exist in the system.
	 */
	private static void readFiles() {
		boolean validFileNames = true;
		do {
			if (!validFileNames) {
				System.out.println("The files entered don't exist on this machine.");
			}
			System.out.print("Please insert a document name: ");
			file = new File(scanner.nextLine());
			System.out.print("Please insert another document name: ");
			file2 = new File(scanner.nextLine());
			validFileNames = false;
		} while (!file.exists() && !file.exists());
	}
}

