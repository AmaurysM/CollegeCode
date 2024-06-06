package util;

import java.util.HashSet;

public class Hash {
	
	private static HashSet<String> hashDictionary = new HashSet<String>();

	public static HashSet<String> getHashDictionary() {
		return Hash.hashDictionary;
	}

	public static void setHashDictionary(HashSet<String> hashDictionary) {
		Hash.hashDictionary = hashDictionary;
	}
	
	public static void addToDictionary(String word) {
		Hash.hashDictionary.add(word);
	}
	
	public static boolean hasWord(String word) {
		return Hash.hashDictionary.contains(word.toLowerCase());
	}
	
}
