package ie.gmit.sw;

import java.util.Set;

public class Document {
	private int id;
	private Set<Integer> shingles;

	public Document(int id, Set<Integer> shingles) {
		this.id = id;
		this.shingles = shingles;
	}

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
