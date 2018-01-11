package ie.gmit.sw;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/*
 * Class that contains two methods along with a constructor method.
 * The constructor method takes one int as a parameter.
 * Creates a TreeSet called minHashes. and adds random numbers into the TreeSet.
 */

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
	/*
	 * Compare method takes two parameters of type Document.
	 * Calculates the minHash on each Document passed in.
	 * Calculates the jaccard index of the two documents by first calculateing the 
	 * intersection of the two sets of minHashes, and dividing it by the size of the minHashes set(200).
	 */
	public float compare(Document doc1, Document doc2) {
		Set<Integer> a = calculateMinHash(doc1);
		Set<Integer> b = calculateMinHash(doc2);
		//to calculate the intersection of the two sets
		Set<Integer> n = new TreeSet<Integer>(b);
		n.retainAll(a);
		return 1.0f * n.size() / minHashes.size();
	}
	
	/*
	 * Method to calculate the minHash of the individual Document objects.
	 * Takes in one Document object as a parameter.
	 * For each int shingle in the minHashes set, calculate the bitwise by using (int minhash = shingle ^ hash;).
	 * Adding the minHash to a set called shingles.
	 * final set of minHashes to be compared for the minHashes of the other document
	 * Return that set of minHashes.
	 */

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
			//
			shingles.add(min);
		}
		return shingles;
	}
}

