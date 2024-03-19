package texteditor;

import java.util.*;
import java.util.Map.Entry;

/**
 * The OutputManager class is responsible for printing texts in the console. It
 * contains all messages stored in data fields and for very message a specific
 * method for printing. It also prints out the paragraphs.
 */
public class OutputManager {
	private final String greetingMessage = "Welcome to text processing";
	private final String errorMessageInvalidArgument = "InvalidArgument";
	private final String errorMessageInvalidCommand = "InvalidCommand";
	private final String exitMessage = "Text processing is closed";
	private final String inputRequest = "> ";
	private final String replaceeRequest = "find: ";
	private final String replacementRequest = "replacement: ";
	private final String nothingToReplaceError = "There is no text";
	private final String argumentRequest = ": ";
	private String paragraphNumbers = "";
	/**
	 * Print paragraphs in the fixed format
	 * 
	 * @param paragraphs contains an arraylist from the type string with all
	 *                   paragraphs
	 */
	public void printParagraphsFixed(List<String> paragraphs) {
		for (String paragraph : paragraphs) {
			System.out.println(paragraph);
		}
	}

	/**
	 * Print paragraphs in the raw format
	 * 
	 * @param paragraphs contains an arraylist from the type string with all
	 *                   paragraphs
	 */
	public void printParagraphsRaw(List<String> paragraphs) {
		for (int i = 0; i < paragraphs.size(); i++) {
			int paragraphNumber = i;
			System.out.println(++paragraphNumber + ": " + paragraphs.get(i));
		}
	}

	/**
	 * Print greeting Message
	 */
	public void printGreeting() {
		System.out.println(greetingMessage);
	}

	/**
	 * Print invalid argument error
	 */
	public void printInvalidArgumentError() {
		System.err.println(errorMessageInvalidArgument);
	}

	/**
	 * Print invalid command error
	 */
	public void printInvalidCommandError() {
		System.err.println(errorMessageInvalidCommand);
	}

	/**
	 * Print the line for the input (line for command) with a >
	 */
	public void printInputRequest() {
		System.out.print(inputRequest);
	}

	/**
	 * Print the line for the input (line for argument) with a :
	 */
	public void printArgumentRequest() {
		System.out.print(argumentRequest);
	}

	/**
	 * Print exit message
	 */
	public void printExitMessage() {
		System.out.println(exitMessage);
	}

	/**
	 * Print the index of all paragraphs
	 * 
	 * @param treeMap contains a string for the words and an arraylist in which
	 *                paragraphs the words are found
	 */
	public void printIndex(TreeMap<String, ArrayList<Integer>> treeMap) {
		if (treeMap.isEmpty()) {
			System.out.println("The index is empty");
		} else {
			for (Entry<String, ArrayList<Integer>> map : treeMap.entrySet()) {
				for (Integer set : map.getValue()) {
					paragraphNumbers += ++set + ",";
				}
				paragraphNumbers = paragraphNumbers.substring(0, paragraphNumbers.length() - 1);
				System.out.println(map.getKey() + " " + paragraphNumbers);
				paragraphNumbers = "";
			}
			paragraphNumbers = "";
		}
	}

	/**
	 * Print the line for input (line for word to replace)
	 */
	public void printReplaceeRequest() {
		System.out.print(replaceeRequest);
	}

	/**
	 * Print the line for input ( line for replacement)
	 */
	public void printReplacementRequest() {
		System.out.print(replacementRequest);
	}

	/**
	 * Print nothing to replace error
	 */
	public void printNothingToReplaceError() {
		System.err.println(nothingToReplaceError);
	}
}
