package ie.gmit.sw;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MinHashJaccardComparer {

	private Set<Integer> minHashes;

	public MinHashJaccardComparer(int numberOfHashes) {
		minHashes = new TreeSet<Integer>();
		//random number for the minhashing
		Random rnd = new Random();
		for (int i = 0; i < numberOfHashes; i++) {
			minHashes.add(rnd.nextInt());
		}
	}

	//to compare the two documents
	public float compare(Document doc1, Document doc2) {
		Set<Integer> a = calculateMinHash(doc1);
		Set<Integer> b = calculateMinHash(doc2);
		//to calculate the intersection of the two sets
		Set<Integer> n = new TreeSet<Integer>(b);
		n.retainAll(a);
		return 1.0f * n.size() / minHashes.size();
	}

	//method to calculate the minHash of a document
	private Set<Integer> calculateMinHash(Document document) {
		Set<Integer> shingles = new TreeSet<Integer>();
		for (Integer hash : minHashes) {
			int min = Integer.MAX_VALUE;
			for (int shingle : document.getShingles()) {
				int minhash = shingle ^ hash;
				if (minhash < min) {
					min = minhash;
				}
			}
			//final set of minHashes to be compared for the minHashes of the other document
			shingles.add(min);
		}
		return shingles;
	}
}

