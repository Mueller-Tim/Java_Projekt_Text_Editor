package texteditor;

import java.util.*;

/**
 * The TextProcessor class is responsible for processing the paragraphs.
 * It can add and delete paragraphs, add a dummy paragraph from a pre-defined string,
 * replace a specific string from the paragraphs and print get an index of words.
 */
public class TextProcessor {

	private final String dummyParagraph = "42 is the Answer to the Ultimate Question of Life, "
			+ "the Universe, and Everything in The Hitchhiker's Guide to the Galaxy books. "
			+ "It was computed by Deep Thought, the second greatest computer ever. "
			+ "The descendants of Deep Thought's creators are upset by the numeric nature of the answer, "
			+ "not understanding what to do with it or what to tell the people who had commissioned "
			+ "the 7.5-million year project.";

	/**
	 * Adds a new paragraph at the specific paragraph number.
	 *
	 * @param paragraphs
	 * @param paragraphNumber
	 * @param newParagraph
	 * @return paragraph list with new added paragraph
	 */
	public ArrayList<String> addParagraph(ArrayList<String> paragraphs, Integer paragraphNumber, String newParagraph) {
		paragraphs.add(paragraphNumber, newParagraph);
		return paragraphs;
	}

	/**
	 * Adds the dummyParagraph at the specific paragraph number.
	 *
	 * @param paragraphs
	 * @param paragraphNumber
	 * @return paragraph list with new added dummy paragraph
	 */
	public ArrayList<String> addDummyParagraph(ArrayList<String> paragraphs, Integer paragraphNumber) {
		paragraphs.add(paragraphNumber, dummyParagraph);
		return paragraphs;
	}

	/**
	 * Deletes a paragraph at the specific paragraph number.
	 *
	 * @param paragraphs
	 * @param paragraphNumber
	 * @return paragraph list without deleted paragraph
	 */
	public ArrayList<String> deleteParagraph(ArrayList<String> paragraphs, Integer paragraphNumber) {
		paragraphs.remove((int) paragraphNumber);
		return paragraphs;
	}

	/**
	 * Generates a collection of word index, in which paragraphs the word occur.
	 * Only words, which occur more than three times will be listed.
	 *
	 * @param paragraphs
	 * @return tree map with word index
	 */
	public TreeMap<String, ArrayList<Integer>> getWordIndex(ArrayList<String> paragraphs) {
		TreeMap<String, ArrayList<Integer>> wordIndexList = new TreeMap<>();
		for (int i = 0; i < paragraphs.size(); i++) {
			String[] words = paragraphs.get(i).split(" ");
			wordIndexList = appendIndex(validateWords(words), wordIndexList, i);
		}
		wordIndexList = removeRareWords(wordIndexList);
		return wordIndexList;
	}

	private ArrayList<String> validateWords(String[] words) {
		ArrayList<String> validWords = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^a-zöäüA-ZÖÄÜ]", "");
			if (!words[i].isBlank() && Character.isUpperCase(words[i].charAt(0))) {
				validWords.add(words[i]);
			}
		}
		return validWords;

	}

	private TreeMap<String, ArrayList<Integer>> appendIndex(ArrayList<String> words, TreeMap<String, ArrayList<Integer>> wordIndexList, int paragraphNumber) {
		HashSet<String> uniqueWords = new HashSet<>(words);
		for (String word : uniqueWords) {
			if (wordIndexList.containsKey(word)) {
				wordIndexList.get(word).add(paragraphNumber);
			} else {
				wordIndexList.put(word, new ArrayList<Integer>());
				wordIndexList.get(word).add(paragraphNumber);
			}
		}
		return wordIndexList;
	}

	private TreeMap<String, ArrayList<Integer>> removeRareWords(TreeMap<String, ArrayList<Integer>> wordIndexList) {
		Set<String> allWords = wordIndexList.keySet();
		Iterator<String> iterator = allWords.iterator();
		while (iterator.hasNext()) {
			if (wordIndexList.get(iterator.next()).size() <= 3) {
				iterator.remove();
			}
		}
		return wordIndexList;
	}

	/**
	 * Replaces a search string in a specific paragraph number with a new string.
	 *
	 * @param paragraphs
	 * @param paragraphNumber
	 * @param textToBeRemoved
	 * @param replacementText
	 * @return paragraph list with replaced strings.
	 */
	public ArrayList<String> replaceString(ArrayList<String> paragraphs, int paragraphNumber, String textToBeRemoved, String replacementText) {
		paragraphs.set(paragraphNumber, paragraphs.get(paragraphNumber).replace(textToBeRemoved, replacementText));
		return paragraphs;
	}

}