package testCases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import texteditor.TextProcessor;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The TextProcessorTest class contains JUnit tests for testing the index function.
 * The equivalence classes that are being tested are the following:
 * 1. paragraphs list is null
 * 2. paragraphs list is empty
 * 3. paragraphs only contain lowercase words
 * 4. ignoring uppercase characters in lowercase words
 * 5. paragraphs only contain uppercase words
 * 6. paragraphs contain upper and lowercase words
 * 7. ignore character set ".,:;-!?’()"%@+*[]{}/\&#$"
 * 8. paragraphs only contain illegal characters
 * 9. paragraphs only contain words which occur 3 or fewer times
 * 10. paragraphs only contain words which occur 4 or more times
 */
class TextProcessorTest {
	private TextProcessor textProcessor;
	private ArrayList<String> wordList;
	private TreeMap<String, ArrayList<Integer>> wordIndexList;
	private final static String[] INVALID_CHARACTER_SET = { ".", ",", ":", ";", "-", "!", "?", "’", "(", ")", "\"",
			"%", "@", "+", "*", "[", "]", "{", "}", "/", "\\", "&", "#", "$"};
	private final static int MIN_COUNTS = 4;

	@BeforeEach
	void setUp() {
		textProcessor = new TextProcessor();
		wordList = new ArrayList<>();
		wordIndexList = new TreeMap<>();
	}

	/**
	 * Equivalence class 1: paragraphs list is null
	 * type: negative test
	 * input: null value
	 * output: NullPointerException
	 */
	@Test
	void getWordIndexNull(){
		assertThrows(NullPointerException.class, () -> textProcessor.getWordIndex(null));
	}

	/**
	 * Equivalence class 2: paragraphs list is empty
	 * type: positive test
	 * input: empty paragraphs ArrayList
	 * output: index TreeMap
	 */
	@Test
	void getWordIndexEmpty(){
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}

	/**
	 * Equivalence class 3: paragraphs only contain lowercase words
	 * type: positive test
	 * input: paragraphs ArrayList
	 * output: empty index TreeMap
	 */
	@Test
	void getWordIndexLowerCase(){
		for(int i = 0; i < MIN_COUNTS; i++){
			wordList.add("lowercase words do not count");

		}
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}

	/**
	 * Equivalence class 4: ignoring uppercase characters in lowercase words
	 * type: positive test
	 * input: paragraphs ArrayList
	 * output: empty index TreeMap
	 */
	@Test
	void getWordIndexUpperCaseEnd(){
		for(int i = 0; i < MIN_COUNTS; i++){
			wordList.add("uPPERCASE iN lOWERCASE wORDS dO nOT cOUNT");

		}
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}

	/**
	 * Equivalence class 5: paragraphs only contain uppercase words
	 * type: positive test
	 * input paragraphs ArrayList
	 * output: index TreeMap with all entries
	 */
	@Test
	void getWordIndexUpperCase(){
		ArrayList<Integer> paragraphNumbers = new ArrayList<>();
		for(int i = 0; i < MIN_COUNTS; i++){
			wordList.add("Uppercase Words Do Count");
			paragraphNumbers.add(i);
		}
		wordIndexList.put("Uppercase", paragraphNumbers);
		wordIndexList.put("Words", paragraphNumbers);
		wordIndexList.put("Do", paragraphNumbers);
		wordIndexList.put("Count", paragraphNumbers);
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}

	/**
	 * Equivalence class 6: paragraphs contain upper and lowercase words
	 * type: positive test
	 * input: paragraphs ArrayList
	 * output: index TreeMap with only uppercase words
	 */
	@Test
	void getWordIndexAllCases(){
		ArrayList<Integer> paragraphNumbers = new ArrayList<>();
		for(int i = 0; i < MIN_COUNTS; i++){
			wordList.add("Uppercase and Lowercase Words mixed");
			paragraphNumbers.add(i);
		}
		wordIndexList.put("Uppercase", paragraphNumbers);
		wordIndexList.put("Lowercase", paragraphNumbers);
		wordIndexList.put("Words", paragraphNumbers);
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}

	/**
	 * Equivalence class 7: ignore character set ".,:;-!?’()"%@+*[]{}/\&#$"
	 * type: positive test
	 * input: paragraphs ArrayList
	 * output: index TreeMap without character set
	 */
	@Test
	void getWordIndexInvalidCharacterSet(){
		ArrayList<Integer> paragraphNumbers = new ArrayList<>();
		for(int i = 0; i < INVALID_CHARACTER_SET.length; i++){
			wordList.add("Upperc" + INVALID_CHARACTER_SET[i] + "ase");
			paragraphNumbers.add(i);
		}
		wordIndexList.put("Uppercase", paragraphNumbers);
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}

	/**
	 * Equivalence class 8: paragraphs only contain illegal characters
	 * type: positive test
	 * input: paragraphs ArrayList
	 * output: empty index TreeMap
	 */
	@Test
	void getWordIndexInvalidCharacter(){
		wordList.add("?");
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}


	/**
	 * Equivalence class 9: paragraphs only contain words which occur 3 or fewer times
	 * type: positive test
	 * input: paragraph ArrayList
	 * output: empty index TreeMap
	 */
	@Test
	void getWordIndexInvalidAppearances(){
		int toFewForTheWordCount = MIN_COUNTS - 1;
		for(int i = 0; i < toFewForTheWordCount; i++){
			wordList.add("Two times the same world is to few for the word counter");

		}
		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}

	/**
	 * Equivalence class 10: paragraphs only contain words which occur 4 or more times
	 * type: positive test
	 * input: paragraphs ArrayList
	 * output: index TreeMap with all words
	 */
	@Test
	void getWordIndexValidAppearances(){
		ArrayList<Integer> paragraphNumbers = new ArrayList<>();
		for(int i = 0; i < MIN_COUNTS + 1; i++){
			wordList.add("Red and Blue");
			paragraphNumbers.add(i);

		}
		wordIndexList.put("Blue", paragraphNumbers);
		wordIndexList.put("Red", paragraphNumbers);

		assertEquals(wordIndexList, textProcessor.getWordIndex(wordList));
	}


}