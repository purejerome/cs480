package text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeSet;

public class WordFinder {
	private TreeSet<String> words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	
	public WordFinder(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        } catch (Exception e) {
            System.err.println("Error reading in the file: " + e.getMessage());
        }
	}
	
	public String find(String prefix) {
		String match = words.ceiling(prefix);
		return (!prefix.isEmpty() && match != null && match.toLowerCase().startsWith(prefix.toLowerCase())) ? match : null;
	}
}
