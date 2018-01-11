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

public class ShingleParser {
	
	private int shingleSize;
	
	public ShingleParser(int shingleSize) {
		this.shingleSize = shingleSize;
	}
	
	public Set<Integer> parse(InputStream stream) throws IOException {
		Set<Integer> shingles = new LinkedHashSet<Integer>();
		List<String> buffer = parseWords(stream);
		StringBuilder sb = new StringBuilder();
		int counter  = 0;
		Iterator<String> iter = buffer.iterator();
		while(iter.hasNext()) {
			if (counter == shingleSize - 1) {
				//add the words in groups of 4(shingleSize) to the shingles set after getting the hashcode of that group of words
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
	
	
	private List<String> parseWords(InputStream stream) throws IOException {
		List<String> buffer = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		//while there is a next line
		while ((line = br.readLine()) != null) {
			//split the lines at the space
			String[] words = line.split(" ");
			for(int i = 0; i < words.length; i++) {
				buffer.add(words[i]);
			}
		}
		return buffer;
	}
}