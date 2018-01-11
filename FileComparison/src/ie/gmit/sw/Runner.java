package ie.gmit.sw;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Runner {
	private static Scanner scanner = new Scanner(System.in);
	private static File file, file2;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int numberOfHashes = 200;
		int shingleSize = 4;
		
		readFiles();
		
		// https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html
		ExecutorService service = Executors.newCachedThreadPool();
		// https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Future.html
		Future<Document> doc1 = service.submit(new Worker(1, file, shingleSize));
		Future<Document> doc2 = service.submit(new Worker(2, file2, shingleSize));

		MinHashJaccardComparer comparer = new MinHashJaccardComparer(numberOfHashes);
		float jaccardIndex = comparer.compare(doc1.get(), doc2.get());
		System.out.printf("The jaccard index for the two documents is %.2f", jaccardIndex);

		scanner.close();
	}

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

