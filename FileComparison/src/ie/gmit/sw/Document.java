/*
 * Simple class used to create a document object so that
 * it can be compared to another document object later on in the program.
 */
package ie.gmit.sw;

import java.util.Set;

public class Document {
	private int id;
	private Set<Integer> shingles;

	/*
	 * constructor consisting of an integer and a set of shingles.
	 */
	
	
	public Document(int id, Set<Integer> shingles) {
		this.id = id;
		this.shingles = shingles;
	}

	/*
	 * Simple getter methods with a toString method for formatting.
	 */
	public int getId() {
		return id;
	}

	public Set<Integer> getShingles() {
		return shingles;
	}

	public String toString() {
		return "Document [id=" + id + ", shingles=" + shingles.size() + "]";
	}
}
