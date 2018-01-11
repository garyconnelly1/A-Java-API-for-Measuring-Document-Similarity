package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * Class that is constructed with only 1 int parameter, shingleSize(4).
 * Uses an InputStream to parse the words in the document to shingles.
 *
 */


public class ShingleParser {
	
	private int shingleSize;
	
	public ShingleParser(int shingleSize) {
		this.shingleSize = shingleSize;
	}
	
	/*
	 * Parse method just takes an input stream as a parameter.
	 * Creates a hashSet called shingles. Calles the method parseWords.
	 * Creates a StringBuilder object and an Iterator object.
	 * If counter == 3 then add the words in groups of 4(shingleSize) 
	 * to the shingles set after getting the hashcode of that group of words
	 * reset counter to 0 and make a new StringBuilder.
	 * Otherwise add the next iterated string to the StringBuilder.
	 * Increment counter.
	 * 
	 */
	
	public Set<Integer> parse(InputStream stream) throws IOException {
		Set<Integer> shingles = new LinkedHashSet<Integer>();
		List<String> buffer = parseWords(stream);
		StringBuilder sb = new StringBuilder();
		int counter  = 0;
		Iterator<String> iter = buffer.iterator();
		while(iter.hasNext()) {
			if (counter == shingleSize - 1) {
				
				shingles.add(sb.toString().toUpperCase().hashCode());
				counter = 0;
				sb = new StringBuilder();
			} else {
				//if counter is not equal to shingleSize-1, append another word to the stringBuilder
				sb.append(iter.next());
				iter.remove();
				counter++;
			}
		}
		return shingles;
	}
	
	/*
	 * Method for how the words should be parsed.
	 * Just takes an InputStream as a parameter.
	 * Creates a BufferedReader object with the input stream as a parameter.
	 * Split the lines at the space.
	 * Adds the words and index [i] to the buffer and returns the buffer.
	 */
	
	private List<String> parseWords(InputStream stream) throws IOException {
		List<String> buffer = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		//while there is a next line
		while ((line = br.readLine()) != null) {
			//
			String[] words = line.split(" ");
			for(int i = 0; i < words.length; i++) {
				buffer.add(words[i]);
			}
		}
		return buffer;
	}
}